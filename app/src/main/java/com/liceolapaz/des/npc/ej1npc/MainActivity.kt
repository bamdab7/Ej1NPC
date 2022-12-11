package com.liceolapaz.des.npc.ej1npc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.liceolapaz.des.npc.ej1npc.databinding.ActivityAddJugadorBinding
import com.liceolapaz.des.npc.ej1npc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Esta clase listara todos los jugadores, con BBDD
    private lateinit var db : SQLiteDatabase
    private lateinit var btnAddJugador : Button
    private lateinit var recyclerView: RecyclerView
    private var adapter : JugadoresAdapter? = null
    private lateinit var sqLiteHelper: JugadoresSQLiteHelper

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sqLiteHelper = JugadoresSQLiteHelper(this, "DBJugadores", null,1)
       // recyclerView = findViewById(R.id.recyclerJugadores)
        //Lanzamos la recycledView
        initRecycledView()

        getJugadores()
        //Se obtiene la referencia a los botones
        btnAddJugador = findViewById(R.id.btnAddJugador)
        //Cuando pulsemos, iremos a una nueva pagina donde podremos insertar jugadores
        btnAddJugador.setOnClickListener{
            val intent = Intent(this@MainActivity,AddJugador::class.java)
            startActivity(intent)
        }
    }

    private fun getJugadores() {
        //Llamamos al metodo que tenemos en la clase de la database donde hace la consulta de busqueda
        val jugadoresLista = sqLiteHelper.getAllJugadores()
        Log.e("Numero de jugadores","${jugadoresLista.size}")
        //Mostraremos los datos en el recycled
        adapter?.addItems(jugadoresLista)
    }

    private fun initRecycledView(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        //Mejor manera de acceder a las vistas:
        adapter = JugadoresAdapter { jugadoresModel -> onItemSelected(jugadoresModel) } // el contenido de jugadores
        binding.recyclerJugadores.layoutManager = manager
        binding.recyclerJugadores.adapter = adapter

        binding.recyclerJugadores.addItemDecoration(decoration)
    }
    //La funcion debera recibir un jugador y me lleva a la de editar
    fun onItemSelected(jugadoresModel: JugadoresModel){
        val intent = Intent(this@MainActivity,EditJugador::class.java)

       //Toast.makeText(this,jugadoresModel.nombre,Toast.LENGTH_LONG).show()
        intent.putExtra("Codigo",jugadoresModel.codigo)
        intent.putExtra("Nombre", jugadoresModel.nombre)
        intent.putExtra("Precio",jugadoresModel.precio)
        intent.putExtra("Posicion",jugadoresModel.posicion)
        intent.putExtra("Puntos",jugadoresModel.puntos)

        startActivity(intent)
    }
}
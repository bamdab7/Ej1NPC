package com.liceolapaz.des.npc.ej1npc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
        setContentView(R.layout.activity_main)

        sqLiteHelper = JugadoresSQLiteHelper(this, "DBJugadores", null,1)

        //Lanzamos la recycledView
        initRecycledView()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
      //  recyclerView = findViewById(R.id.recyclerJugadores)
        //Mejor manera de acceder a las vistas:
        binding.recyclerJugadores.layoutManager = LinearLayoutManager(this)
        binding.recyclerJugadores.adapter = JugadoresAdapter()
    }
}
package com.liceolapaz.des.npc.ej1npc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    //Esta clase listara todos los jugadores, con BBDD
    private lateinit var db : SQLiteDatabase

    private lateinit var btnAddJugador : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Abrir la base de datos
        val usdbh = JugadoresSQLiteHelper(this, "DBJugadores",null,1)
        db = usdbh.writableDatabase

        //Se obtiene la referencia a los botones
        btnAddJugador = findViewById(R.id.btnAddJugador)
        //Cuando pulsemos, iremos a una nueva pagina donde podremos insertar jugadores
        btnAddJugador.setOnClickListener{
            val intent = Intent(this@MainActivity2,AddJugador::class.java)
            startActivity(intent)
        }
    }
}
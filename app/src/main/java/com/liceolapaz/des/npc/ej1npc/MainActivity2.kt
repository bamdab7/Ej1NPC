package com.liceolapaz.des.npc.ej1npc

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    //esta clase listara todos los jugadores, con BBDD
    private lateinit var db : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //abrir la base de datos
        val usdbh = JugadoresSQLiteHelper(this, "DBJugadores",null,1)
        db = usdbh.writableDatabase
    }
}
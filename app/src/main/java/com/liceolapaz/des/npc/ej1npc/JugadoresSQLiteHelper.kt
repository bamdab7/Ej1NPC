package com.liceolapaz.des.npc.ej1npc

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class JugadoresSQLiteHelper (
    context : Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int) : SQLiteOpenHelper(context,name,factory,version) {

    //Sentencia SQL para crear la base de datos de jugadores
    val sqlCreate = "CREATE TABLE Jugadores (codigo INTEGER PRIMARY KEY, nombre TEXT, precio DOUBLE, posicion TEXT CHECK(posicion IN('Portero','Defensa','MedioCampista','Delantero')), puntos INTEGER)"

    override fun onCreate(db: SQLiteDatabase?) {
        //Se ejecuta la sentencia SQL de creacion de la tabla
        db?.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //Eliminamos la tabla anterior y la actualizamos
        db?.execSQL("DROP TABLE IF EXISTS Jugadores")
        //Se crea nueva version de la tabla
        db?.execSQL(sqlCreate)
    }
}
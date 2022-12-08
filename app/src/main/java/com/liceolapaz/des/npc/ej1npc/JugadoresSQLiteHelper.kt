package com.liceolapaz.des.npc.ej1npc

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

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

    //Consulta de lista todos los jugadores de nuestra bbdd
    fun getAllJugadores() : ArrayList<JugadoresModel>{
        val jugadoresList : ArrayList<JugadoresModel> = ArrayList()
        val selectQuery = "SELECT * FROM Jugadores"
        val db = this.writableDatabase

        val cursor : Cursor = db.rawQuery(selectQuery,null)

        if(cursor.moveToFirst()){
            do{
                val codigo = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val precio = cursor.getDouble(2)
                val posicion = cursor.getString(3)
                val puntos = cursor.getInt(4)

                val jugador = JugadoresModel(codigo, nombre, precio, posicion, puntos)
                jugadoresList.add(jugador)
                Log.e("Jugador","${jugador}")
            }while (cursor.moveToNext())
        }
        return jugadoresList
    }
}
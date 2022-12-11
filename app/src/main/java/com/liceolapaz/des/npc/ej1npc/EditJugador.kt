package com.liceolapaz.des.npc.ej1npc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class EditJugador : AppCompatActivity() {

    private lateinit var textNombreJugador : TextView
    private lateinit var txtCodigo : EditText
    private lateinit var txtNombre : EditText
    private lateinit var txtPrecio : EditText
    private lateinit var spinnerPosicion : Spinner
    private lateinit var txtPuntos : EditText

    private lateinit var btnAceptar : Button
    private lateinit var btnCancelar : Button
    private lateinit var btnEliminar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_jugador)

        textNombreJugador = findViewById(R.id.textNombreJugador)
        txtCodigo = findViewById(R.id.txtCodigo)
        txtNombre = findViewById(R.id.txtNombre)
        txtPrecio = findViewById(R.id.txtPrecio)
        spinnerPosicion = findViewById(R.id.spinnerPosicion)
        txtPuntos = findViewById(R.id.txtPuntos)

        textNombreJugador.text = intent.getStringExtra("Nombre")

        val codigo = intent.getIntExtra("Codigo",0)
        txtCodigo.setText(codigo.toString())
        txtCodigo.isEnabled = false

        val nombre = intent.getStringExtra("Nombre")
        txtNombre.setText(nombre)

        val precio = intent.getDoubleExtra("Precio",0.0)
        txtPrecio.setText(precio.toString())

        val position = intent.getStringExtra("Posicion")
        if(intent.getStringExtra("Posicion") == "Portero"){
            spinnerPosicion.getItemAtPosition(0)
            spinnerPosicion.setSelection(0)
        }else if(intent.getStringExtra("Posicion") == "Defensa"){
            spinnerPosicion.getItemAtPosition(1)
            spinnerPosicion.setSelection(1)
        }else if(intent.getStringExtra("Posicion") == "MedioCampista"){
            spinnerPosicion.getItemAtPosition(2)
            spinnerPosicion.setSelection(2)
        }else if(intent.getStringExtra("Posicion") == "Delantero"){
            spinnerPosicion.getItemAtPosition(3)
            spinnerPosicion.setSelection(3)
        }
        Log.e("posicion",position.toString())

        val puntos = intent.getIntExtra("Puntos",0)
        txtPuntos.setText(puntos.toString())

    }
}
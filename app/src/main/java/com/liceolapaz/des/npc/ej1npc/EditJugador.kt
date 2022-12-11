package com.liceolapaz.des.npc.ej1npc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        
    }
}
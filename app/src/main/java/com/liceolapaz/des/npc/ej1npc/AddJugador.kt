package com.liceolapaz.des.npc.ej1npc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsSpinner
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class AddJugador : AppCompatActivity() {

    //Declaramos la funcionalidad del boton aceptar + el de cancelar
    private lateinit var txtCodigo : EditText
    private lateinit var txtNombre : EditText
    private lateinit var txtPrecio : EditText
    private lateinit var spinner: Spinner
    private lateinit var txtPuntos : EditText
    private lateinit var btnAceptar : Button
    private lateinit var btnCancelar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_jugador)

        //Instanciamos
        txtCodigo = findViewById(R.id.txtCodigo)
        txtNombre = findViewById(R.id.txtNombre)
        txtPrecio = findViewById(R.id.txtPrecio)
        spinner = findViewById(R.id.spinnerPosicion)
        txtPuntos = findViewById(R.id.txtPuntos)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnCancelar = findViewById(R.id.btnCancelar)
    }
}
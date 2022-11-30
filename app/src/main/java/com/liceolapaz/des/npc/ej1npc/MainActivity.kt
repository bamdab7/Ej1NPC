package com.liceolapaz.des.npc.ej1npc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    //esta clase abre un formulario en el que se pide que se loggeee un usuario 'admin' con contraseña 'liceo'
    //si se introduce otro usuario, saldra un mensaje de error. Si se introduce 3 veces mal, se sale de la app


    //DECLARACION DE VARIABLES
    private lateinit var txtUsername : EditText
    private lateinit var txtPassword : EditText
    private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //LOCALIZAMOS LAS VARIABLES
        txtUsername = findViewById(R.id.editTextUsername)
        txtPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.button)
            //FUNCIONALIDAD BOTON
        btnLogin.setOnClickListener{
            val user = txtUsername.text.toString()
            val pass = txtPassword.text.toString()

            //COMPROBADOR
            if(user == "admin" && pass == "liceo"){
                //PASAMOS SIGUIENTE VISTA
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }
}
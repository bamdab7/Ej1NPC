package com.liceolapaz.des.npc.ej1npc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //esta clase abre un formulario en el que se pide que se loggeee un usuario 'admin' con contraseña 'liceo'
    //si se introduce otro usuario, saldra un mensaje de error. Si se introduce 3 veces mal, se sale de la app


    //DECLARACION DE VARIABLES
    private lateinit var txtUsername : EditText
    private lateinit var txtPassword : EditText
    private lateinit var txtWrongPass : TextView
    private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //LOCALIZAMOS LAS VARIABLES
        txtUsername = findViewById(R.id.editTextUsername)
        txtPassword = findViewById(R.id.editTextPassword)
        txtWrongPass = findViewById(R.id.textWrongPass)
        btnLogin = findViewById(R.id.button)

        //INTENTOS (se van restando)
        var intentos : Int = 3

           //FUNCIONALIDAD BOTON
        btnLogin.setOnClickListener{
            val user = txtUsername.text.toString()
            val pass = txtPassword.text.toString()
            //COMPROBADOR
            if(user == "admin" && pass == "liceo"){
                //PASAMOS SIGUIENTE VISTA
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
            }else{
                --intentos
                txtWrongPass.text = "Usuario y/o contraseña incorrectos"
                if(intentos ==0){
                    finishAndRemoveTask()
                }
            }
        }
    }
}
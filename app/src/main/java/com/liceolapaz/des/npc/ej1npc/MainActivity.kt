package com.liceolapaz.des.npc.ej1npc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //esta clase abre un formulario en el que se pide que se loggeee un usuario 'admin' con contraseña 'liceo'
    //si se introduce otro usuario, saldra un mensaje de error. Si se introduce 3 veces mal, se sale de la app

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
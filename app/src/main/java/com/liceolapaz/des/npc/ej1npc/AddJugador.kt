package com.liceolapaz.des.npc.ej1npc

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

    //la base de datos
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_jugador)

        //Instanciamos
        txtCodigo = findViewById<View>(R.id.txtCodigo) as EditText
        txtNombre = findViewById<View>(R.id.txtNombre) as EditText
        txtPrecio = findViewById<View>(R.id.txtPrecio) as EditText
        spinner = findViewById<View>(R.id.spinnerPosicion) as Spinner
        txtPuntos = findViewById<View>(R.id.txtPuntos) as EditText
        btnAceptar = findViewById<View>(R.id.btnAceptar) as Button
        btnCancelar = findViewById<View>(R.id.btnCancelar) as Button

        //Una vez seleccionado el boton de aceptar, se debera recoger toda la informacion y elaborar la consulta de insert y
        //devolver a la main_activity2

        //primero inicializamos la base de datos
        val usdbh = JugadoresSQLiteHelper(this, "DBJugadores", null,1)
        db = usdbh.writableDatabase

        //Este es el boton de insertar y que luego devolverá a la otra ventana
        btnAceptar.setOnClickListener{
            //Recuperaremos los valores de los campos de texto
            val cod = txtCodigo.text.toString()
            val nom = txtNombre.text.toString()
            val prec = txtPrecio.text.toString()
            val pos = spinner.selectedItem.toString()
            val pun = txtPuntos.text.toString()

            //Ejecutamos la consulta
//            val sqlInsert = "INSERT INTO Jugadores(codigo,nombre,precio,posicion,puntos) VALUES ('$cod','$nom',$prec','$pos','$pun')"
//            db.execSQL(sqlInsert)
            val nuevoRegistro = ContentValues()
            nuevoRegistro.put("codigo",cod)
            nuevoRegistro.put("nombre",nom)
            nuevoRegistro.put("precio",prec)
            nuevoRegistro.put("posicion",pos)
            nuevoRegistro.put("puntos",pun)
            db.insert("Jugadores", null, nuevoRegistro)

        }

    }
}
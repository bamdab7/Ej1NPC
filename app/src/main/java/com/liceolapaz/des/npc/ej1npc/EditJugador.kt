package com.liceolapaz.des.npc.ej1npc

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
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

    //la base de datos
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_jugador)

        //primero inicializamos la base de datos
        val usdbh = JugadoresSQLiteHelper(this, "DBJugadores", null,1)
        db = usdbh.writableDatabase


        textNombreJugador = findViewById(R.id.textNombreJugador)
        txtCodigo = findViewById(R.id.txtCodigo)
        txtNombre = findViewById(R.id.txtNombre)
        txtPrecio = findViewById(R.id.txtPrecio)
        spinnerPosicion = findViewById(R.id.spinnerPosicion)
        txtPuntos = findViewById(R.id.txtPuntos)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnEliminar = findViewById(R.id.btnEliminar)

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

        btnAceptar.setOnClickListener{
            //Este boton hace un update
            dialogoAceptar()
        }
        btnEliminar.setOnClickListener{
            //Este boton hace un delete
            dialogoEliminar()
        }
        btnCancelar.setOnClickListener{
            dialogoCancelar()
        }
    }

    private fun dialogoCancelar() {
        val builder = AlertDialog.Builder(this@EditJugador)
        builder.setTitle("Cancelar")
        builder.setMessage("Los datos no se guardaran, esta seguro de cancelar?")
        //Si ejecutamos si-> Volvera a la lista sin haber eliminado nada
        builder.setPositiveButton("Si"){_,_ -> onBackPressed()}
        //Si elegimos no-> volveremos al formulario de creacion
        builder.setNegativeButton("No"){dialog,_ -> dialog.dismiss()}

        //Lanzamos dialogo
        val dialog = builder.create()
        dialog.show()
    }

    private fun dialogoEliminar() {
        val builder = AlertDialog.Builder(this@EditJugador)
        builder.setTitle("Eliminar")
        builder.setMessage("Los datos se borraran de la base de datos, esta seguro?")
        //Si pulsamos Si -> Volvera a la lsita tras hacer un delete
        builder.setPositiveButton("Si", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                eliminarJugadores()
            }
            private fun eliminarJugadores() {
                //Recueraremos los valores de los campos de texto
                val cod = txtCodigo.text.toString()
                //Ejecutamos la consulta
                val sqlDelete = "DELETE FROM Jugadores WHERE codigo = '$cod'"
                db.execSQL(sqlDelete)
                val intent = Intent(this@EditJugador,MainActivity::class.java)
                startActivity(intent)
            }
        })
        //Si ejecutamos No-> Volvera a la lista sin haber eliminado nada
        builder.setNegativeButton("No"){_,_ -> onBackPressed()}
        //Si elegimos cancelar-> volveremos al formulario de creacion
        builder.setNeutralButton("Cancelar"){dialog,_ -> dialog.dismiss()}

        //Lanzamos dialogo
        val dialog = builder.create()
        dialog.show()
    }

    private fun dialogoAceptar() {
        val builder = AlertDialog.Builder(this@EditJugador)
        builder.setTitle("Aceptar")
        builder.setMessage("Los datos se guardaran en la base de datos, esta seguro?")
        //Si pulsamos Si -> volvera a la lista tras hacer un update
        builder.setPositiveButton("Si", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
               actualizarJugadores()
            }
            private fun actualizarJugadores() {
                //Recueraremos los valores de los campos de texto
                val cod = txtCodigo.text.toString()
                val nom = txtNombre.text.toString()
                val prec = txtPrecio.text.toString()
                val pos = spinnerPosicion.selectedItem.toString()
                val pun = txtPuntos.text.toString()

                //Ejecutamos la consulta
                val sqlUpdate = "UPDATE Jugadores SET nombre = '$nom', precio = '$prec', posicion = '$pos',  puntos = '$pun'  WHERE codigo = '$cod' "
                db.execSQL(sqlUpdate)
                val intent = Intent(this@EditJugador,MainActivity::class.java)
                startActivity(intent)
            }
        })
        //Si elegimos No -> volvera a la lista sin haber guardado los datos
        builder.setNegativeButton("No"){_,_ -> onBackPressed()}
        //Si elegimos cancelar-> volveremos al formulario de creacion
        builder.setNeutralButton("Cancelar"){dialog,_ -> dialog.dismiss()}

        //Lanzamos dialogo
        val dialog = builder.create()
        dialog.show()
    }
}
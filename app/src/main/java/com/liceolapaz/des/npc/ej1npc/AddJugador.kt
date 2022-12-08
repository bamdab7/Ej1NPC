package com.liceolapaz.des.npc.ej1npc

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

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


        //primero inicializamos la base de datos
        val usdbh = JugadoresSQLiteHelper(this, "DBJugadores", null,1)
        db = usdbh.writableDatabase

        //Este es el boton de insertar y que luego devolverá a la otra ventana
        btnAceptar.setOnClickListener{
            dialogoAceptar()
        }

        btnCancelar.setOnClickListener{
            dialogoCancelar()
        }
    }

    //Aqui crearemos un dialogo en el que nos mostrarán 2 mensajes
    private fun dialogoCancelar() {
        val builder = AlertDialog.Builder(this@AddJugador)
        builder.setTitle("Cancelar")
        builder.setMessage("Los datos no se guardaran, esa seguro de querer cancelar?")
        //Si elegimos SI -> se volvera a la lista sin haber guardado los datos
        builder.setPositiveButton("Si"){_,_ ->
            onBackPressed()
        }
        //Si elegimos NO -> se volvera al formulario de creacion
        builder.setNegativeButton("No"){dialog,_ ->
            dialog.dismiss()
        }

        //Lanzamos el dialogo
        val dialog = builder.create()
        dialog.show()
    }

    //Aqui crearemos el dialogo de aceptar en el que nos saldran tres mensajes
    private fun dialogoAceptar() {
       val builder = AlertDialog.Builder(this@AddJugador)
        builder.setTitle("Aceptar")
        builder.setMessage("Los datos se guardaran en la base de datos, esta seguro?")
        //Si pulsamos SI -> volvera a la lista tras guardar los datos
        builder.setPositiveButton("Si", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
               insertarJugadores()
            }
            private fun insertarJugadores() {
                //Recuperaremos los valores de los campos de texto
                val cod = txtCodigo.text.toString()
                val nom = txtNombre.text.toString()
                val prec = txtPrecio.text.toString()
                val pos = spinner.selectedItem.toString()
                val pun = txtPuntos.text.toString()

                //Ejecutamos la consulta
                    // val sqlInsert = "INSERT INTO Jugadores(codigo,nombre,precio,posicion,puntos) VALUES ('$cod','$nom',$prec','$pos','$pun')"
                    // db.execSQL(sqlInsert)
                val nuevoRegistro = ContentValues()
                nuevoRegistro.put("codigo", cod)
                nuevoRegistro.put("nombre", nom)
                nuevoRegistro.put("precio", prec)
                nuevoRegistro.put("posicion", pos)
                nuevoRegistro.put("puntos", pun)
                db.insert("Jugadores", null, nuevoRegistro)
                //Una vez pulsado el boton, iremos a la ventana de todos los jugadores donde se listaran
                    // val intent = Intent(this@AddJugador,MainActivity2::class.java)
                    //  startActivity(intent)
                onBackPressed()
            }
        })
        //Si elegimos NO -> volvera a la lista sin haber guardado los datos
        builder.setNegativeButton("No"){_,_ -> onBackPressed()}
        //Si elegimos Cancelar -> volveremos al formulario de creacion
        builder.setNeutralButton("Cancelar"){dialog, _ -> dialog.dismiss()}

        //Lanzamos el dialogo
        val dialog = builder.create()
        dialog.show()
    }
}
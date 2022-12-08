package com.liceolapaz.des.npc.ej1npc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JugadoresAdapter : RecyclerView.Adapter<JugadoresAdapter.JugadoresViewHolder>() {
    private var jugadoresList : ArrayList<JugadoresModel> = ArrayList()

    fun addItems(items:ArrayList<JugadoresModel>){
        this.jugadoresList = items
    }
    //Crearemos la vista como tal
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JugadoresViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_jugadores, parent, false)
    )

    override fun onBindViewHolder(holder: JugadoresViewHolder, position: Int) {
      //  val jugador = jugadoresList[position]
        holder.bindView(jugadoresList[position])
    }

    override fun getItemCount(): Int {
        return jugadoresList.size
    }

    class JugadoresViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        //Recogemos las variables del card items
        private var nombre = view.findViewById<TextView>(R.id.tvNombre)
        private var precio = view.findViewById<TextView>(R.id.tvPrecio)
        private var posicion = view.findViewById<TextView>(R.id.tvPosicion)
        private var puntos = view.findViewById<TextView>(R.id.tvPuntos)

        fun bindView(jugador: JugadoresModel){
            //Obtenemos los valores
            nombre.text = jugador.nombre
            precio.text = jugador.precio.toString()
            posicion.text = jugador.posicion
            puntos.text = jugador.puntos.toString()
        }
    }
}

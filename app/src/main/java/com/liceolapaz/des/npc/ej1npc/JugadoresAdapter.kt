package com.liceolapaz.des.npc.ej1npc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liceolapaz.des.npc.ej1npc.databinding.CardItemsJugadoresBinding

class JugadoresAdapter (private val onClickListener : (JugadoresModel) -> Unit): RecyclerView.Adapter<JugadoresAdapter.JugadoresViewHolder>() {
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
        holder.render(jugadoresList[position],onClickListener)
    }

    override fun getItemCount(): Int {
        return jugadoresList.size
    }

    class JugadoresViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val binding = CardItemsJugadoresBinding.bind(view)
        //Recogemos las variables del card items
//        var nombre = view.findViewById<TextView>(R.id.tvNombre)
//        var precio = view.findViewById<TextView>(R.id.tvPrecio)
//        var posicion = view.findViewById<TextView>(R.id.tvPosicion)
//        var puntos = view.findViewById<TextView>(R.id.tvPuntos)

        fun render(jugador: JugadoresModel,onClickListener : (JugadoresModel) -> Unit){
            //Obtenemos los valores
//            nombre.text = jugador.nombre
//            precio.text = jugador.precio.toString()
//            posicion.text = jugador.posicion
//            puntos.text = jugador.puntos.toString()
            binding.tvNombre.text = jugador.nombre
            binding.tvPrecio.text = jugador.precio.toString()
            binding.tvPosicion.text = jugador.posicion
            binding.tvPuntos.text = jugador.puntos.toString()

            itemView.setOnClickListener{
                onClickListener(jugador)
            }
        }
    }
}

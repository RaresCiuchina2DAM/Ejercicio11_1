package com.example.ejercicio11

//CREAR UN ADAPTADOR PARA EL RECYCLERVIEW QUE MUESTRE LOS MENSAJES ENVIADOS Y RECIBIDOS POR EL BOT Y EL JUGADOR

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(private val listaMensajes: ArrayList<String>, private val jugador: Jugador) : RecyclerView.Adapter<Adaptador.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mensaje: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mensaje.text = listaMensajes[position]
    }

    override fun getItemCount(): Int {
        return listaMensajes.size
    }
}


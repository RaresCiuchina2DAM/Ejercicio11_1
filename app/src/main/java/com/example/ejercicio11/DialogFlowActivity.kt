package com.example.ejercicio11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio11.databinding.ActivityDialogFlowBinding

class DialogFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDialogFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugador = intent.getSerializableExtra("jugador") as Jugador

        val listaMensajes = ArrayList<String>()
        listaMensajes.add("Hola, soy ${jugador.nombre} y quiero jugar a la ruleta rusa")
        listaMensajes.add("¿Quieres jugar a la ruleta rusa?")
        listaMensajes.add("¡Vaya, no te gusta jugar a la ruleta rusa!")
        listaMensajes.add("¡Vale!, ¡Vamos a jugar!")

        val adaptador = Adaptador(listaMensajes, jugador)
        binding.recyclerChat.adapter = adaptador
        binding.recyclerChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerChat.setHasFixedSize(true)



        //cuando se pulsa el botón enviar se añade el mensaje a la lista de mensajes y el bot nos responderá con un mensaje aleatorio
        binding.enviar.setOnClickListener {
            listaMensajes.add(binding.cajadetexto.text.toString())
            adaptador.notifyDataSetChanged()
            binding.cajadetexto.setText("")
            binding.recyclerChat.scrollToPosition(listaMensajes.size - 1)
            val respuesta = when ((0..3).random()) {
                0 -> "Hola, soy ${jugador.nombre} y quiero jugar a la ruleta rusa"
                1 -> "¿Quieres jugar a la ruleta rusa?"
                2 -> "¡Vaya, no te gusta jugar a la ruleta rusa!"
                else -> "¡Vale!, ¡Vamos a jugar!"
            }
            listaMensajes.add(respuesta)
            adaptador.notifyDataSetChanged()
            binding.recyclerChat.scrollToPosition(listaMensajes.size - 1)
        }


    }

}
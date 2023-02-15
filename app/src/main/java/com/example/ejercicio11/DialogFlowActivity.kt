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

        val adaptador = Adaptador(listaMensajes, jugador)
        binding.recyclerChat.adapter = adaptador
        binding.recyclerChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerChat.setHasFixedSize(true)



        //cuando se pulsa el botón enviar se añade el mensaje a la lista de mensajes y el bot nos responderá con un mensaje aleatorio
        binding.enviar.setOnClickListener {
            listaMensajes.add(binding.cajadetexto.text.toString())
            adaptador.notifyDataSetChanged()

            when (binding.cajadetexto.text.toString()) {
                "si" -> {
                    val respuesta = "¡Vale!, ¡Vamos a jugar!"
                    listaMensajes.add(respuesta)
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.setText("")
                    binding.recyclerChat.scrollToPosition(listaMensajes.size - 1)
                }
                "no" -> {
                    val respuesta = "¡Vaya, no te gusta jugar a la ruleta rusa!"
                    listaMensajes.add(respuesta)
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.setText("")
                    binding.recyclerChat.scrollToPosition(listaMensajes.size - 1)
                }
                else -> {
                    val respuesta = "No te he entendido"
                    listaMensajes.add(respuesta)
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.setText("")
                    binding.recyclerChat.scrollToPosition(listaMensajes.size - 1)
                }
            }

        }


    }

}
package com.example.ejercicio11

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio11.databinding.ActivityDialogFlowBinding

class DialogFlowActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDialogFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugador = intent.getSerializableExtra("jugador") as Jugador

        val listaMensajes = ArrayList<String>()
        listaMensajes.add("Hola, soy ${jugador.nombre} y soy tu personaje")
        listaMensajes.add("Puedes preguntarme cosas sobre mi \ny también puedes elegir que quieres hacer")
        listaMensajes.add("¿Que quieres hacer? (Pulsa el numero de la opción que quieras)")
        listaMensajes.add("1. Hablar con mi personaje")
        listaMensajes.add("2. Viajar a otra ciudad")
        listaMensajes.add("3. Luchar contra un enemigo")
        listaMensajes.add("4. Ir a la tienda")
        listaMensajes.add("5. Ir a caminar por el bosque")
        listaMensajes.add("6. Despedirme de mi personaje y salir del juego")


        val adaptador = Adaptador(listaMensajes, jugador)
        binding.recyclerChat.adapter = adaptador
        binding.recyclerChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerChat.setHasFixedSize(true)


        //cuando se pulsa el botón enviar se añade el mensaje a la lista de mensajes y el bot nos responderá con un mensaje aleatorio
        binding.enviar.setOnClickListener {
            listaMensajes.add(binding.cajadetexto.text.toString())
            adaptador.notifyDataSetChanged()
            when (binding.cajadetexto.text.toString()) {
                "1" -> {
                    //cuando se pulsa el botón enviar se añade el mensaje a la lista de mensajes y el bot nos responderá con un mensaje aleatorio
                    val intent = Intent(this, SobreActivity::class.java)
                    listaMensajes.add("¡Vamos a hablar de mi!")
                    adaptador.notifyDataSetChanged()
                    intent.putExtra("jugador", jugador)
                    startActivity(intent)
                    finish()
                }
                "2" -> {
                    val intent = Intent(this, CiudadActivity::class.java)
                    intent.putExtra("jugador", jugador)
                    listaMensajes.add("¡Vamos a viajar!")
                    adaptador.notifyDataSetChanged()
                    startActivity(intent)
                    finish()
                }
                "3" -> {
                    val intent = Intent(this, EnemigoActivity::class.java)
                    listaMensajes.add("¡Vamos a luchar!")
                    adaptador.notifyDataSetChanged()
                    intent.putExtra("jugador", jugador)
                    startActivity(intent)
                    finish()
                }
                "4" -> {
                    val intent = Intent(this, MercaderActivity::class.java)
                    listaMensajes.add("¡Vamos a la tienda!")
                    adaptador.notifyDataSetChanged()
                    intent.putExtra("jugador", jugador)
                    startActivity(intent)
                    finish()
                }
                "5" -> {
                    val intent = Intent(this, ObjetoActivity::class.java)
                    listaMensajes.add("¡Vamos a caminar!")
                    adaptador.notifyDataSetChanged()
                    intent.putExtra("jugador", jugador)
                    startActivity(intent)
                    finish()
                }
                "6" -> {
                    val intent = Intent(this, MainActivity::class.java)
                    listaMensajes.add("¡Hasta pronto!")
                    startActivity(intent)
                    finish()
                }
                else -> {
                    listaMensajes.add("No has elegido una opción válida")
                    adaptador.notifyDataSetChanged()
                }

            }

        }


    }
}















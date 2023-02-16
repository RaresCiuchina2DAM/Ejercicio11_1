package com.example.ejercicio11

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio11.databinding.ActivitySobreBinding
import java.util.*

class SobreActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySobreBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val jugador = intent.getSerializableExtra("jugador") as Jugador

        //se crea la lista de mensajes
        val listaMensajes = ArrayList<String>()
        listaMensajes.add("Has elegido hablar con el ${jugador.nombre}")

        //se crea el adaptador y se le pasa la lista de mensajes y el jugador
        val adaptador = Adaptador(listaMensajes, jugador)
        binding.recyclerChat.adapter = adaptador
        binding.recyclerChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerChat.setHasFixedSize(true)

        //cuando se click el botón de enviar mensaje, se añade el mensaje a la lista de mensajes
        binding.enviar.setOnClickListener {
            listaMensajes.add(binding.cajadetexto.text.toString())
            adaptador.notifyDataSetChanged()

            when (binding.cajadetexto.text.toString()) {
                "¿Cuantos años tienes?" -> {
                    listaMensajes.add("Tengo ${jugador.edad} años")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "¿Cual es tu clase?" -> {
                    if (jugador.raza == "") {
                        listaMensajes.add("OH NO! No conozco mi clase, ¡vamos a averiguarla!")
                        adaptador.notifyDataSetChanged()
                        val intent = Intent(this, EligeclaseActivity::class.java)
                        intent.putExtra("jugador", jugador)
                        startActivity(intent)
                    } else
                        listaMensajes.add("Soy ${jugador.raza}")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "Sabes hablar en el idioma de tu raza?" -> {
                    if (jugador.raza == "") {
                        listaMensajes.add("OH NO! No conozco mi raza, ¡vamos a averiguarla!")
                        adaptador.notifyDataSetChanged()
                        val intent = Intent(this, EligerazaActivity::class.java)
                        intent.putExtra("jugador", jugador)
                        startActivity(intent)
                    } else
                        listaMensajes.add("Sí, se hablar en el idioma de mi raza: ${jugador.raza}")
                    adaptador.notifyDataSetChanged()
                    listaMensajes.add(
                        hablarConJugador(
                            "¿Mira, esto es en mi idioma, ¿lo entiendes?",
                            jugador
                        )
                    )
                    binding.cajadetexto.text.clear()
                }
                "No lo entiendo" -> {
                    listaMensajes.add("Por eso he tenido que aprender a hablar en tu idioma")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "Cual es tu clase?" -> {
                    listaMensajes.add("Soy ${jugador.clase}")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "¿Cuales son tur armas?" -> {
                    listaMensajes.add("Mis armas son ${jugador.mochila}")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "¿Cual es tu fuerza?" -> {
                    listaMensajes.add("Mi fuerza es ${jugador.fuerza}")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "¿Cual es tu defensa?" -> {
                    listaMensajes.add("Mi defensa es ${jugador.defensa}")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "¿Cuantas partidas ganadas tienes?" -> {
                    listaMensajes.add("Tengo ${jugador.partidasGanadasEnCiudad} partidas ganadas")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "¿Cuanto dinero tienes?" -> {
                    listaMensajes.add("Tengo ${monederoToInt(jugador.monedero)} pavos")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "¿Cuando naciste?" -> {
                    //restar la fecha actual a la edad del jugador

                    val fecha = (2023 - jugador.edad).toString()
                    listaMensajes.add("Nací en el $fecha")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }
                "Quiero irme" -> {
                    listaMensajes.add("vale, adiós")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                    val intent = Intent(this, DialogFlowActivity::class.java)
                    intent.putExtra("jugador", jugador)
                    startActivity(intent)
                    finish()
                }
                "¿Cual es tu arma favorita?" -> {
                    listaMensajes.add("Mi arma favorita es ${jugador.mochila.random()}")
                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()

                }


                else -> {
                    listaMensajes.add("No te entiendo")
                    listaMensajes.add("Preguntame cosas como:")
                    listaMensajes.add(
                        "¿Cual es tu arma favorita?\n" +
                                " ¿Cuantos años tienes?\n" +
                                " ¿Cual es tu raza?\n" +
                                " ¿Sabes hablar en el idioma de tu raza?\n" +
                                " ¿Cual es tu clase?\n" +
                                " ¿Cuales son tur armas?\n" +
                                " ¿Cual es tu fuerza?\n" +
                                " ¿Cual es tu defensa?\n" +
                                " ¿Cuantas partidas ganadas tienes?\n" +
                                " ¿Cuanto dinero tienes?\n" +
                                " ¿Cuando naciste?\n" +
                                " o: 'Quiero irme' para salir"
                    )

                    adaptador.notifyDataSetChanged()
                    binding.cajadetexto.text.clear()
                }

            }
        }
    }


}

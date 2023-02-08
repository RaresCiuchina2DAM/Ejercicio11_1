package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityResumenBinding
import com.google.gson.Gson
import java.io.Serializable


class ResumenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResumenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val jugador = Jugador("","","")
        //recoger el objeto jugador que se ha pasado por el intent
        val jugadorRecibido = intent.getSerializableExtra("jugador") as Jugador

        //asignar los valores del jugador recibido al jugador de esta actividad
        jugador.raza = jugadorRecibido.raza
        jugador.nombre = jugadorRecibido.nombre
        jugador.clase = jugadorRecibido.clase



        //Cuando la clase pasada por el intent sea humano, asignar al Imageview la imagen de persona

        when (jugador.clase) {
            null-> {
                binding.ImagenClase.setImageResource(R.drawable.inicio)
            }
            "ladron" -> {
                binding.ImagenClase.setImageResource(R.drawable.descarga)
            }
            "mago" -> {
                binding.ImagenClase.setImageResource(R.drawable.mago)
            }
            "guerrero" -> {
                binding.ImagenClase.setImageResource(R.drawable.guerrero)
            }
            "berserker" -> {
                binding.ImagenClase.setImageResource(R.drawable.berserker)
            }
        }

//
//
//        Cuando la raza pasada por el intent sea humano, asignar al Imageview la imagen de persona
        when (jugador.raza) {
            null-> {
                binding.ImagenClase.setImageResource(R.drawable.inicio)
            }
            "humano" -> {
                binding.ImagenRaza.setImageResource(R.drawable.persona)
            }
            "elfo" -> {
                binding.ImagenRaza.setImageResource(R.drawable.elfo)
            }
            "enano" -> {
                binding.ImagenRaza.setImageResource(R.drawable.enano)
            }
            "goblin" -> {
                binding.ImagenRaza.setImageResource(R.drawable.goblin)
            }
        }
//
//
//        Cada vez que se pulsa el boton asignar, se asigna el nombre que se ha introducido en el edittext
        binding.btnAsignar.setOnClickListener {
            binding.MostrarNombreAsignado.text = binding.EtNombre.text
            jugador.nombre = binding.EtNombre.text.toString()

        }

        binding.tvfuerza.text = jugador.fuerza.toString()
        binding.tvdefensa.text = jugador.defensa.toString()
        binding.tvtamanyoMochila.text = jugador.tamanyoMochila.toString()
        binding.tvvida.text = jugador.vida.toString()
        binding.tvmonedero.text = jugador.monedero.toString()
//
        jugador.mochila = arrayListOf(Objeto(""))



        binding.button.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)

        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}
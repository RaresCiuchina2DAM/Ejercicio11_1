package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityDadoBinding


class DadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //recoger el objeto jugador que se ha pasado por el intent
        val jugador = intent.getSerializableExtra("jugador") as Jugador



        binding.imageView.setImageResource(R.drawable.inicio)
        //Al pulsar el dado se ejecutará una función aleatoria que devolverá un número del 1 al 4

        binding.btonDado.setOnClickListener {
//            val numero = (1..4).random()
           // val uno = Intent(this, ObjetoActivity::class.java)
//            val dos = Intent(this, CiudadActivity::class.java)
            val tres = Intent(this, MercaderActivity::class.java)
//            val cuatro = Intent(this, EnemigoActivity::class.java)
//
//            when (numero) {
//                1 -> {
//                    uno.putExtra("jugador", jugador)
//                    startActivity(uno)
//                }
//                2 -> {
//                    dos.putExtra("jugador", jugador)
//                    startActivity(dos)
//                }
//                3 -> {
                    tres.putExtra("jugador", jugador)
                    startActivity(tres)
//                }
//                4 -> {
//                    cuatro.putExtra("jugador", jugador)
//                    startActivity(cuatro)
//                }
//            }
        }
    }
}
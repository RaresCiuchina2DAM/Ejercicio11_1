package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityCiudadaleatorizadaBinding

class CiudadaleatorizadaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCiudadaleatorizadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recoger el objeto jugador que se ha pasado por el intent
        val jugador = intent.getSerializableExtra("jugador") as Jugador

        /*
        Si se pulsa el botón "Entrar" se aleatorizará la aparición de objetos,
         mercaderes o enemigos (normales o jefes). Para poder "completar" la ciudad debe
         haberse luchado con 5 enemigos, independientemente de los otros eventos.
         */
        while (jugador.partidasGanadasEnCiudad < 5) {
            when (aleatorizar(3)) {
                1 -> {
                    val intent = Intent(this, ObjetoActivity::class.java)
                    intent.putExtra("jugador", jugador)
                    intent.putExtra("ciudad", "ciudad")
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this, MercaderActivity::class.java)
                    intent.putExtra("jugador", jugador)
                    intent.putExtra("ciudad", "ciudad")
                    startActivity(intent)
                }
                3 -> {
                    val intent = Intent(this, EnemigoActivity::class.java)
                    intent.putExtra("jugador", jugador)
                    intent.putExtra("ciudad", "ciudad")
                    startActivity(intent)

                }
            }
        }


    }


}

//funcion aleatorizar que devolverá un número del 1 al 4
fun aleatorizar(int: Int): Int {
    val numero = (1..int).random()
    return numero
}
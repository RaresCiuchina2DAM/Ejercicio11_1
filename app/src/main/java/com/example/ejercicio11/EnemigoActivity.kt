package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityEnemigoBinding

class EnemigoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemigoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jugador = Jugador("", "", "")
        //recoger el objeto jugador que se ha pasado por el intent
        val jugadorRecibido = intent.getSerializableExtra("jugador") as Jugador

        //asignar los valores del jugador recibido al jugador de esta actividad
        jugador.raza = jugadorRecibido.raza
        jugador.nombre = jugadorRecibido.nombre
        jugador.clase = jugadorRecibido.clase
        jugador.mochila = jugadorRecibido.mochila
        jugador.monedero = jugadorRecibido.monedero
        jugador.defensa = jugadorRecibido.defensa
        jugador.tamanyoMochila = jugadorRecibido.tamanyoMochila
        jugador.vida = jugadorRecibido.vida
        jugador.fuerza = jugadorRecibido.fuerza


        //si pulsas el botón Luchar se continua a la actividad blanca
        //si pulsas el botón Huir se vuelve a la actividad dado

        binding.BtnLuchar.setOnClickListener {
            val intent = Intent(this, BlancaActivity::class.java)
            startActivity(intent)
        }
        binding.BtnHuir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)
        }


    }
}
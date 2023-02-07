package com.example.ejercicio11


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.EligerazaActividad2Binding
import com.google.gson.Gson


class EligerazaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = EligerazaActividad2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugador = Jugador("","","")


        binding.imageView.setImageResource(R.drawable.inicio)


        binding.BtnElfo.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.elfo)
            jugador.raza = "elfo"
        }

        binding.BtnEnano.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.enano)
            jugador.raza = "enano"
        }

        binding.BtnGoblin.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.goblin)
            jugador.raza = "goblin"
        }

        binding.BtnHumano.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.persona)
            jugador.raza = "humano"
        }


        binding.btnAceptar.setOnClickListener {
            val intent = Intent(this, EligeclaseActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)
            finish()
        }


    }

}


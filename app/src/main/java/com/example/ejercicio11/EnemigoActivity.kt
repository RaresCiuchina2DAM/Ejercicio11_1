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


        //si pulsas el botón Luchar se continua a la actividad blanca
        //si pulsas el botón Huir se vuelve a la actividad dado

        binding.BtnLuchar.setOnClickListener {
            val intent = Intent(this, BlancaActivity::class.java)
            startActivity(intent)
        }
        binding.BtnHuir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }


    }
}
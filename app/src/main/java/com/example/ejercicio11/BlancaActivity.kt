package com.example.ejercicio11

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityBlancaBinding

class BlancaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBlancaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val jugador = intent.getSerializableExtra("jugador") as Jugador

        binding.listaObjetos.text = jugador.mochila.toString()




    }


}
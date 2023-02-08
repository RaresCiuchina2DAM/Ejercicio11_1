package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.ejercicio11.databinding.ActivityBlancaBinding

class BlancaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBlancaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //recoger el objeto con un bundle que se ha pasado por el intent



        if (intent == intenta){
            //mostrar la lista de objetos
            jugador.mochila.forEach {
                binding.listaObjetos.text = it.toString()
            }




        }

    }
}
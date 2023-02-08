package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.example.ejercicio11.databinding.ActivityMercaderBinding

class MercaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMercaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Invisibilizamos los botones de comprar y vender y cancelar
        binding.BotonesComprarVender.visibility = View.INVISIBLE
        //recoger el objeto jugador que se ha pasado por el intent
        val jugador = intent.getSerializableExtra("jugador") as Jugador



        binding.BtnComerciar.setOnClickListener {
            val intent = Intent(this, BlancaActivity::class.java)
            startActivity(intent)
            //Hacemos que el botón Continuar y el boton comerciar se vuelvan invisibles
            binding.BotonesComerciaryContinuar.visibility = View.INVISIBLE
            //Hacemos que el botón de comprar y vender y cancelar se vuelvan visibles
            binding.BotonesComprarVender.visibility = View.VISIBLE

            //copiamos la posicion de los botonesComerciaryContinuar y los pegamos en los botonesComprarVender
            binding.BtnComprar.layoutParams = binding.BtnComerciar.layoutParams
            binding.BtnVender.layoutParams = binding.BtnContinuar.layoutParams

            }





        binding.BtnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)
        }


    }



}
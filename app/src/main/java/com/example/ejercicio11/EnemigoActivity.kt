package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityEnemigoBinding
import kotlinx.coroutines.delay

class EnemigoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemigoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val jugador = intent.getSerializableExtra("jugador") as Jugador
        var a = 0

        val random = (0..9).random()

        if (random <= 4){
            binding.imagenObjeto.setImageResource(R.drawable.enemigo1)
            binding.Enemigo.text = "Enemigo"
            binding.progressBarEnemigo.progress = 100
            binding.progressBarEnemigo.max = 100
            a = 1
        } else {
            binding.imagenObjeto.setImageResource(R.drawable.enemigo2)
            binding.Enemigo.text = "Enemigo Jefazo"
            binding.progressBarEnemigo.progress = 200
            binding.progressBarEnemigo.max = 200
            a = 2
        }

        binding.BtnLuchar.setOnClickListener {
            val intent = Intent(this, Enemigo2Activity::class.java)
            intent.putExtra("jugador", jugador)
            intent.putExtra("tipo_enemigo", a)
            intent.putExtra("random", random)
            startActivity(intent)
        }
        binding.BtnHuir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)
        }


    }
}
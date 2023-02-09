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


        val jugador = intent.getSerializableExtra("jugador") as Jugador

        //crear un random entre 0 y 9
        val random_elegido = (0..9).random()
        if (random_elegido < 5) {
            binding.imagenObjeto.setImageResource(R.drawable.enemigo1)
            binding.Enemigo.text = "Enemigo"
            binding.progressBar.progress=100
        } else {
            binding.imagenObjeto.setImageResource(R.drawable.enemigo2)
            binding.Enemigo.text = "Enemigo Jefazo"
            binding.progressBar.progress=200
        }

        binding.BtnLuchar.setOnClickListener {
            val intent = Intent(this, Enemigo2Activity::class.java)
            intent.putExtra("jugador", jugador)
            intent.putExtra("random_elegido", random_elegido)
            startActivity(intent)
        }
        binding.BtnHuir.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)
        }


    }
}
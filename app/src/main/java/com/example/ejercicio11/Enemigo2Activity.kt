package com.example.ejercicio11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ejercicio11.databinding.ActivityEnemigo2Binding

class Enemigo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemigo2Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_enemigo2)

        var jugador = intent.getSerializableExtra("jugador") as Jugador
        var random_elegido = intent.getIntExtra("random_elegido", 0)

        if (random_elegido < 5) {
            binding.imagenObjeto.setImageResource(R.drawable.enemigo1)
            binding.Enemigo.text = "Enemigo"
            binding.progressBarEnemigo.progress=100
        } else {
            binding.imagenObjeto.setImageResource(R.drawable.enemigo2)
            binding.Enemigo.text = "Enemigo Jefazo"
            binding.progressBarEnemigo.progress=200
        }

        if (binding.progressBarEnemigo.progress <= 0){
            binding.Enemigo.text = "Enemigo muerto"
        }
        binding.BtnAtacar.setOnClickListener{
            var ataque = (1..6).random()
            if (ataque >= 4){
                binding.progressBarEnemigo.progress -= jugador.fuerza
            }else{
                binding.progressBarEnemigo.progress -= 0
            }

        }
        binding.BtnHuir.setOnClickListener{
            var huir = (1..6).random()
            if (huir >= 5){
                Toast.makeText(this, "Has huido", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DadoActivity::class.java)
                intent.putExtra("jugador", jugador)
                startActivity(intent)
            }else {
                Toast.makeText(this, "No has podido huir", Toast.LENGTH_SHORT).show()
            }

        }

        binding.ConsumirObjeto.setOnClickListener{
            //bjeto: cada objeto almacenado en la mochila permite sumar vida al usuario hasta su límite por defecto. Cada objeto consumido suma 20 puntos de vida.

            if (jugador.mochila.size > 0){
                if (jugador.vida < jugador.vida_max){
                }
                    jugador.vida += jugador.mochila[0].vida
                    jugador.mochila.removeAt(0)
                    Toast.makeText(this, "Has consumido un objeto", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Tu vida esta al maximo", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
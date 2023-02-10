package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityEnemigo2Binding

class Enemigo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEnemigo2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugador = intent.getSerializableExtra("jugador") as Jugador
        val random = intent.getIntExtra("random", 0)


        Toast.makeText(
            this,
            "${jugador.nombre} !! \n Has encontrado un enemigo",
            Toast.LENGTH_SHORT
        ).show()

        var vidaEnemigo = 0
        if (random <= 4) {
            binding.imagenObjeto.setImageResource(R.drawable.enemigo1)
            binding.Enemigo.text = "Enemigo"
            binding.progressBarEnemigo.progress = 100
            binding.progressBarEnemigo.max = 100
            vidaEnemigo = 100
        } else {
            binding.imagenObjeto.setImageResource(R.drawable.enemigo2)
            binding.Enemigo.text = "Enemigo Jefazo"
            binding.progressBarEnemigo.progress = 200
            binding.progressBarEnemigo.max = 200
            vidaEnemigo = 200
        }


        binding.BtnAtacar.setOnClickListener {

            //Ataque jugador
            val ataque = (1..6).random()
            if (ataque >= 4) {
                binding.progressBarEnemigo.progress -= jugador.fuerza
                vidaEnemigo -= jugador.fuerza
                if (vidaEnemigo <= 0) {
                    Toast.makeText(this, "¡HAS CONSEGUIDO VENCER AL ENEMIGO!", Toast.LENGTH_SHORT)
                        .show()
                    jugador.monedero = IntToMonedero(100)
                    if (jugador.mochila.contains(Objeto("Pocion de vida"))) {
                        jugador.mochila.remove(Objeto("Pocion de vida"))
                    }
                    if (jugador.mochila.contains(Objeto("Pocion de fuerza"))) {
                        jugador.mochila.remove(Objeto("Pocion de fuerza"))
                    }
                    if (jugador.mochila.contains(Objeto("Pocion de defensa"))) {
                        jugador.mochila.remove(Objeto("Pocion de defensa"))
                    }
                    jugador.mochila.add(Objeto("Pocion de vida"))
                    jugador.mochila.add(Objeto("Pocion de fuerra"))
                    jugador.mochila.add(Objeto("Pocion de defensa"))
                    val intent = Intent(this, DadoActivity::class.java)
                    intent.putExtra("jugador", jugador)
                    startActivity(intent)
                }
                Toast.makeText(this, "DALE!! Has atacado", Toast.LENGTH_SHORT).show()

                //Ataque enemigo
                val ataqueEnemigo = (1..6).random()
                if (ataqueEnemigo >= 4) {
                    if (random <= 4) {
                        binding.progressBarJugador.progress -= 20 / jugador.defensa
                        jugador.vida -= 20 / jugador.defensa
                    } else {
                        binding.progressBarJugador.progress -= 30 / jugador.defensa
                        jugador.vida -= 40 / jugador.defensa
                    }
                    if (jugador.vida <= 0) {
                        Toast.makeText(this, "¡HAS MUERTO!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    Toast.makeText(this, "¡Te han atacado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "¡El enemigo ha fallado!", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Ups!! Casi le das!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.BtnHuir.setOnClickListener {
            val huir = (1..6).random()
            if (huir >= 5) {
                Toast.makeText(
                    this,
                    "COBARDE! Te has ido corriendo!,\n te estará esperando",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, DadoActivity::class.java)
                intent.putExtra("jugador", jugador)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Tienes que quedarte a luchar!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.ConsumirObjeto.setOnClickListener {

            if (jugador.mochila.size > 0) {
                if (jugador.vida < jugador.vida_max) {
                    jugador.vida += jugador.mochila[0].vida
                    jugador.mochila.removeAt(0)
                    Toast.makeText(this, "Has consumido un objeto", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Tu vida esta al maximo", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }
}



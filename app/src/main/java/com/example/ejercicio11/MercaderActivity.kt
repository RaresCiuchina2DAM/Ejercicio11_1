package com.example.ejercicio11

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityMercaderBinding

val PRECIOCOMPRAROBJETO = 125

class MercaderActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMercaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Invisibilizamos los botones de comprar y vender y cancelar
        binding.BotonesComprarVender.visibility = View.INVISIBLE
        binding.BotonesMasMenos.visibility = View.INVISIBLE
        //recoger el objeto jugador que se ha pasado por el intent
        val jugador = intent.getSerializableExtra("jugador") as Jugador



        binding.BtnComerciar.setOnClickListener {

            //Hacemos que el botón Continuar y el boton comerciar se vuelvan invisibles
            binding.BotonesComerciaryContinuar.visibility = View.INVISIBLE
            //Hacemos que el botón de comprar y vender y cancelar se vuelvan visibles
            binding.BotonesComprarVender.visibility = View.VISIBLE

            val mapaObjetos = mutableMapOf<Int, String>()
            mapaObjetos[R.drawable.arma] = "Arma"
            mapaObjetos[R.drawable.armadura] = "Armadura"
            //mapaObjetos[R.drawable.armadura_oro] = "Armadura de oro"
            mapaObjetos[R.drawable.espada_diamante] = "Espada de Diamante"
            mapaObjetos[R.drawable.lanza] = "Lanza"
            mapaObjetos[R.drawable.pocion] = "Poción"
            mapaObjetos[R.drawable.talisman] = "Talismán"

            //generar un número aleatorio entre 0 y 6
            var random_elegido = (0 until mapaObjetos.size).random()
            //asignar el objeto aleatorio a la imagen
            binding.imagenObjeto.setImageResource(mapaObjetos.keys.elementAt(random_elegido))


            binding.BtnCancelar.isPressed = false
            binding.BtnComprar.setOnClickListener {


                binding.BotonesMasMenos.visibility = View.VISIBLE
                var cantidad = 0
                binding.NumeroVeces.text = cantidad.toString()
                binding.BtnMas.setOnClickListener {
                    //Aumentamos el valor de la variable cantidad
                    cantidad++
                    binding.NumeroVeces.text = cantidad.toString()
                }
                binding.BtnMenos.setOnClickListener {
                    if (cantidad > 0) {
                        //Disminuimos el valor de la variable cantidad
                        cantidad--
                        binding.NumeroVeces.text = cantidad.toString()
                    } else
                        Toast.makeText(
                            this,
                            "No puedes comprar menos de 0 objetos",
                            Toast.LENGTH_SHORT
                        ).show()
                    binding.NumeroVeces.text = cantidad.toString()
                }

                binding.BtnComprar.text = "CONFIRMAR COMPRA"

                binding.BtnComprar.setOnClickListener {

                    val objetoSeleccionado =
                        Objeto(mapaObjetos.values.elementAt(random_elegido))
                    objetoSeleccionado.valor = PRECIOCOMPRAROBJETO
                    if (cantidad == 0) {
                        binding.BtnComprar.isEnabled = false
                        if (binding.NumeroVeces.text != 0.toString()){}

                    } else if (monederoToInt(jugador.monedero) >= objetoSeleccionado.valor * cantidad) {
                        if (jugador.tamanyoMochila >= objetoSeleccionado.peso) {
                            jugador.monedero =
                                IntToMonedero(monederoToInt(jugador.monedero) - objetoSeleccionado.valor * cantidad)
                            jugador.mochila.add(objetoSeleccionado)
                            Toast.makeText(
                                this,
                                "Has comprado $cantidad : $objetoSeleccionado.nombre",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.BtnComprar.text = "COMPRAR"
                        } else
                            Toast.makeText(
                                this,
                                "No tienes espacio en la mochila",
                                Toast.LENGTH_SHORT
                            ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "No tienes suficiente dinero en el monedero",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }

            }

            binding.BtnCancelar.setOnClickListener {
                binding.BtnCancelar.isPressed = true
                binding.BotonesComerciaryContinuar.visibility = View.VISIBLE
                binding.BotonesComprarVender.visibility = View.INVISIBLE
                binding.BotonesMasMenos.visibility = View.INVISIBLE
            }
        }
    }
    }




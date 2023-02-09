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
                        Toast.makeText(
                            this,
                            "No puedes comprar 0 objetos",
                            Toast.LENGTH_SHORT
                        ).show()
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

            //cuando se pulse el boton de vender se realizaran los mismos pasos que en la parte del boton comprar pero se redirigirá a la mochila

            binding.BtnVender.setOnClickListener {

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
                            "No puedes vender menos de 0 objetos",
                            Toast.LENGTH_SHORT
                        ).show()
                    binding.NumeroVeces.text = cantidad.toString()
                }


                binding.BtnVender.text = "CONFIRMAR VENTA"

                binding.BtnVender.setOnClickListener {

                    val objetoSeleccionado =
                        Objeto(mapaObjetos.values.elementAt(random_elegido))
                    objetoSeleccionado.valor = PRECIOCOMPRAROBJETO
                    if (cantidad == 0) {
                        binding.BtnVender.isEnabled = false
                        Toast.makeText(
                            this,
                            "No puedes vender 0 objetos",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (jugador.mochila.isEmpty()) {
                        Toast.makeText(
                            this,
                            "No tienes objetos en la mochila",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        var objetosEnLaMochila = jugador.mochila.count()
                        //crear hasmap con los objetos de la mochila y su valor

                        var mapaObjetosMochila = HashMap<String, Int>()
                        for (i in 0 until objetosEnLaMochila) {
                            mapaObjetosMochila.put(
                                jugador.mochila[i].nombre,
                                jugador.mochila[i].valor
                            )
                        }
                        //recoger el valor de los objetos del mapa y sumarlo en una variable SumaValorMochila
                        var sumaValorMochila = 0
                        for (i in 0 until objetosEnLaMochila) {
                            sumaValorMochila += mapaObjetosMochila.values.elementAt(i)
                        }
                        if (cantidad <= objetosEnLaMochila) {
                            jugador.monedero += IntToMonedero(monederoToInt(jugador.monedero) + sumaValorMochila * cantidad)
                            for (i in 0 until cantidad) {
                                jugador.mochila.removeAt(i)
                            }
                            Toast.makeText(
                                this,
                                "Has vendido un total de $cantidad , con un valor total de $sumaValorMochila",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.BtnVender.text = "VENDER"
                        } else
                            Toast.makeText(
                                this,
                                "No tienes suficientes objetos en la mochila",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                }


                binding.BtnCancelar.setOnClickListener {
                    binding.BotonesMasMenos.visibility = View.INVISIBLE
                    binding.BotonesComerciaryContinuar.visibility = View.VISIBLE
                    binding.BotonesComprarVender.visibility = View.INVISIBLE
                    binding.BtnVender.text = "VENDER"
                    binding.BtnVender.isEnabled = true
                    binding.BtnComprar.text = "COMPRAR"
                    binding.BtnVender.isEnabled = true

                }
            }
        }
    }
}



package com.example.ejercicio11

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityObjetoBinding

class ObjetoActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugador = Jugador("", "", "")
        //recoger el objeto jugador que se ha pasado por el intent
        val jugadorRecibido = intent.getSerializableExtra("jugador") as Jugador

        //asignar los valores del jugador recibido al jugador de esta actividad
        jugador.tamanyoMochila = jugadorRecibido.tamanyoMochila
        jugador.raza = jugadorRecibido.raza
        jugador.nombre = jugadorRecibido.nombre
        jugador.clase = jugadorRecibido.clase
        jugador.mochila = jugadorRecibido.mochila
        jugador.monedero = jugadorRecibido.monedero
        jugador.defensa = jugadorRecibido.defensa
        jugador.vida = jugadorRecibido.vida
        jugador.fuerza = jugadorRecibido.fuerza



        //crear un hasmap para poder asignarle un nombre a cada imagen
        val mapaObjetos = mutableMapOf<Int, String>()
        mapaObjetos[R.drawable.arma] = "Arma"
        mapaObjetos[R.drawable.armadura] = "Armadura"
        mapaObjetos[R.drawable.armadura_oro] = "Armadura de oro"
        mapaObjetos[R.drawable.espada_diamante] = "Espada de Diamante"
        mapaObjetos[R.drawable.lanza] = "Lanza"
        mapaObjetos[R.drawable.pocion] = "Poción"
        mapaObjetos[R.drawable.talisman] = "Talismán"

        //generar un número aleatorio entre 0 y 6
        var random_elegido = (0 until mapaObjetos.size).random()
        //asignar el objeto aleatorio a la imagen
        binding.imagenObjeto.setImageResource(mapaObjetos.keys.elementAt(random_elegido))
        //asignar el nombre del objeto a la variable Objeto
        binding.Objeto.text = mapaObjetos.values.elementAt(random_elegido)
        //ver capacidad restante de la mochila
        val sumpeso = 100 - jugador.sumPeso
        binding.CapacidadMochila.text = "     $sumpeso/100"


        binding.BtnRecoger.setOnClickListener {

            //Creamos el objeto que se va a recoger
            val objetoSeleccionado = Objeto(mapaObjetos.values.elementAt(random_elegido))

            //recogemos el objeto
            recoger(jugador, objetoSeleccionado, this)

            //eliminamos el objeto de la lista de objetos
            mapaObjetos.remove(mapaObjetos.keys.elementAt(random_elegido))

            //Actualizamos
            var sumpeso = 100 - jugador.sumPeso
            binding.CapacidadMochila.text = "     $sumpeso/100"

            binding.Contenidodelamochila.text = jugador.mochila.toString()

            //repetimos codigo para generar un objeto cada vez que se pulsa el botón recoger, hasta que no queden objetos en la lista o se llene la mochila

            //si no quedan objetos en la lista, se deshabilita el botón de recoger
            if (mapaObjetos.isEmpty()) {
                binding.BtnRecoger.isEnabled = false
                Toast.makeText(this, "No existen más objetos", Toast.LENGTH_LONG).show()

            } else{
                random_elegido = (0 until mapaObjetos.size).random()
                binding.imagenObjeto.setImageResource(mapaObjetos.keys.elementAt(random_elegido))
                binding.Objeto.text = mapaObjetos.values.elementAt(random_elegido)
                sumpeso = 100 - jugador.sumPeso
                binding.CapacidadMochila.text = "     $sumpeso/100"
                binding.Contenidodelamochila.text = jugador.mochila.toString()

            }

        }


        binding.BtnContinuar.setOnClickListener {

            //si el intent de donde viene es de la ciudad, se vuelve a la ciudad
            if (intent.getStringExtra("ciudad") == "ciudad") {
                val intent = Intent(this, CiudadaleatorizadaActivity::class.java)
                intent.putExtra("jugador", jugador)
                startActivity(intent)
            } else {
                val intent = Intent(this, DadoActivity::class.java)
                intent.putExtra("jugador", jugador)
                startActivity(intent)
            }

        }
    }

}

/*
@function Función que recoge un objeto de la lista, un jugador y el objeto que se quiere recoger,
comprueba si hay espacio en la mochila y si lo hay, añade el objeto a la mochila y lo elimina de la lista de objetos
si no hay espacio, lanza un toast con el mensaje de que no hay espacio
@param jugador: Jugador, listaObjetos: MutableList<Objeto>, objeto: Objeto
@return jugador: Jugador
 */
fun recoger(
    jugador /*Jugador que quieras añadirle el objeto*/: Jugador,
    objeto /*objeto*/: Objeto,
    contexto  /* contexto*/: Context
): Jugador {


    //Se comprueba que el objeto que se quiere recoger quepa dentro de la capacidad de la mochila

    if (jugador.tamanyoMochila < objeto.peso) {
        //si no cabe, se lanza un toast con el mensaje de que no cabe
        Toast.makeText(contexto, "No cabe en la mochila", Toast.LENGTH_SHORT).show()

    } else
        {
        //si cabe, se añade el objeto a la mochila
        jugador.mochila.add(objeto)
        jugador.monedero += IntToMonedero(objeto.valor)
        Toast.makeText(contexto, "Has recogido $objeto", Toast.LENGTH_SHORT).show()
        // Se resta el peso del objeto a la capacidad de la mochila del jugador
        jugador.sumPeso += objeto.peso

    }

    return jugador
}






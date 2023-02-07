package com.example.ejercicio11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityObjetoBinding

class ObjetoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //cada vez que abramos la actividad, se pondrá un objeto aleatorio del arraay en el imageView


        var jugador = Jugador("", "", "")
        //recoger el objeto jugador que se ha pasado por el intent
        val jugadorRecibido = intent.getSerializableExtra("jugador") as Jugador

        //asignar los valores del jugador recibido al jugador de esta actividad
        jugador.raza = jugadorRecibido.raza
        jugador.nombre = jugadorRecibido.nombre
        jugador.clase = jugadorRecibido.clase
        jugador.mochila = jugadorRecibido.mochila
        jugador.monedero = jugadorRecibido.monedero
        jugador.defensa = jugadorRecibido.defensa
        jugador.tamanyoMochila = jugadorRecibido.tamanyoMochila
        jugador.vida = jugadorRecibido.vida
        jugador.fuerza = jugadorRecibido.fuerza


        var random_elegido = (0..6).random()
        //crear un hasmap para poder asignarle un nombre a cada imagen
        val mapaObjetos = mutableMapOf<Int, String>()
        mapaObjetos.put(R.drawable.arma, "Arma")
        mapaObjetos.put(R.drawable.armadura, "Armadura")
        mapaObjetos.put(R.drawable.armadura_oro, "Armadura de oro")
        mapaObjetos.put(R.drawable.espada_diamante, "Espada de Diamante")
        mapaObjetos.put(R.drawable.lanza, "Lanza")
        mapaObjetos.put(R.drawable.pocion, "Poción")
        mapaObjetos.put(R.drawable.talisman, "Talismán")

        //asignar el objeto aleatorio a la imagen
        binding.imagenObjeto.setImageResource(mapaObjetos.keys.elementAt(random_elegido))
        //asignar el nombre del objeto a la variable nombreObjeto
        binding.Objeto.text = mapaObjetos.values.elementAt(random_elegido)
        //ver capacidad restante de la mochila
        binding.CapacidadMochila.text = "     " + jugador.tamanyoMochila.toString() + "/" + 100



        binding.BtnRecoger.setOnClickListener {
            //Creamos el objeto que se va a recoger
            val objetoSeleccionado = Objeto(mapaObjetos.values.elementAt(random_elegido))

            //recogemos el objeto
            jugador = recoger(jugador, objetoSeleccionado, this)

            //eliminamos el objeto de la lista de objetos
            mapaObjetos.remove(mapaObjetos.keys.elementAt(random_elegido))

            //Vamos a la actividad dado
            val intent = Intent(this, DadoActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)


        }

        //si pulsas el botón Continuar se vuelve a la actividad dado
        binding.BtnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            intent.putExtra("jugador", jugador)
            startActivity(intent)
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
        Toast.makeText(contexto, "No cabe en la mochila", Toast.LENGTH_LONG).show()
    } else if (jugador.tamanyoMochila >= objeto.peso) {
        //si cabe, se añade el objeto a la mochila
        jugador.mochila.add(objeto)
        Toast.makeText(contexto, "Has recogido ${objeto}", Toast.LENGTH_LONG).show()
        // Se resta el peso del objeto a la capacidad de la mochila del jugador
        jugador.tamanyoMochila - objeto.peso

    }

    return jugador
}






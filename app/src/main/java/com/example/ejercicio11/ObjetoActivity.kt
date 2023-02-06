package com.example.ejercicio11

import android.content.Intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityObjetoBinding

class ObjetoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val jugador = Jugador("","","")
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


        val aDrawable = arrayOf(R.drawable.lanza,R.drawable.arma)

        val listaObjetos = mutableListOf<Objeto>(Objeto("Lanza"), Objeto("Arma"))


        //cada vez que abramos la actividad, se pondrá un objeto aleatorio del arraay en el imageView
        binding.imagenObjeto.setImageResource(aDrawable.random())

        if (binding.imagenObjeto.equals(aDrawable[0])) {
            binding.Objeto.text = listaObjetos[0].nombre
        } else {
            binding.Objeto.text = listaObjetos[1].nombre
        }

        binding.BtnRecoger.setOnClickListener {



        }

        //si pulsas el botón Continuar se vuelve a la actividad dado
        binding.BtnContinuar.setOnClickListener {
            val intent = Intent(this, DadoActivity::class.java)
            startActivity(intent)
        }
    }
}

fun robar(Ladron: Jugador, articulos: MutableList<Objeto>): MutableList<Objeto> {
    //Saca la rentabilidad de cada objeto y lo añade en orden a un array del mismo tamaño que el original
    val rentabilidad = Array<Double>(size = articulos.size) { 0.00 }
    for (i in articulos.indices) {
        rentabilidad[i] = (articulos[i].valor / articulos[i].peso).toDouble()
    }


    //Recorre el array una y otra vez, hasta que no entre nada más en la mochila
    while (Ladron.tamanyoMochila > Ladron.sumPeso && rentabilidad.max() != 0.0) {
        for (i in rentabilidad.indices) {

            if (rentabilidad[i] == rentabilidad.max() && rentabilidad[i] != 0.0) {
                if (Ladron.sumPeso + articulos[i].peso <= Ladron.tamanyoMochila) {
                    Ladron.sumPeso += articulos[i].peso
                    Ladron.sumValor += articulos[i].valor

                    Ladron.mochila.add(articulos[i])
                    rentabilidad[i] = 0.0
                    articulos.removeAt(i)
                } else
                    rentabilidad[i] = 0.0

            }
        }

    }

    return Ladron.mochila

}
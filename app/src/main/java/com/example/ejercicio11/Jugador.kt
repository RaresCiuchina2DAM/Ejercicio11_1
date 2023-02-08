package com.example.ejercicio11

import android.os.Parcelable
import java.io.Serializable

class Jugador : Serializable
    {
        var raza: String
        var clase: String
        var nombre: String
        var defensa: Int = (1..5).random()
        var fuerza: Int = (10..15).random()
        var vida: Int = 200
        var monedero: HashMap<String, Int>
        var tamanyoMochila: Int = 100
        lateinit var mochila: MutableList<Objeto>
        var sumPeso = 0
        var sumValor = 0


        constructor(nombre: String, raza: String, clase: String) {
            this.nombre = nombre
            this.raza = raza
            this.clase = clase

        }
        init {
            monedero = HashMap()
        }
    }

class Objeto : java.io.Serializable {

    var peso: Int = 0
    var valor: Int = 0
    var vida: Int = 0
    var nombre: String


    constructor (nombre: String) {
        this.nombre = nombre
    }

    override fun toString(): String {
        return "El Objeto con Nombre='$nombre',\t con peso=$peso,\t con valor=$valor,\t y con vida=$vida)"
    }
    init {

        vida = 20
        peso = 5
        valor = 10
    }

}

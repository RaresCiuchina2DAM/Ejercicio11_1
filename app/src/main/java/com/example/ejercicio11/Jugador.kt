package com.example.ejercicio11

import android.os.Parcelable
import java.io.Serializable

class Jugador : Serializable {
    var raza: String
    var clase: String
    var nombre: String
    var defensa: Int = (1..5).random()
    var fuerza: Int = (10..15).random()
    var vida: Int = 200
    lateinit var monedero: HashMap<Int, Int>
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
        monedero[1] = 0
        monedero[5] = 0
        monedero[10] = 0
        monedero[25] = 0
        monedero[100] = 0
    }

    //Funcion para añadir monedas al monedero si se vende un objeto
    fun venderObjeto(objeto: Objeto) {
        var valor = objeto.valor
        var monedas = 0
        while (valor > 0) {
            if (valor >= 100) {
                monedas = valor / 100
                monedero[100] = monedero[100]!! + monedas
                valor -= monedas * 100
            }
            if (valor >= 25) {
                monedas = valor / 25
                monedero[25] = monedero[25]!! + monedas
                valor -= monedas * 25
            }
            if (valor >= 10) {
                monedas = valor / 10
                monedero[10] = monedero[10]!! + monedas
                valor -= monedas * 10
            }
            if (valor >= 5) {
                monedas = valor / 5
                monedero[5] = monedero[5]!! + monedas
                valor -= monedas * 5
            }
            if (valor >= 1) {
                monedas = valor / 1
                monedero[1] = monedero[1]!! + monedas
                valor -= monedas * 1
            }
        }
    }

    //funcion para vender objetos de la mochila

    fun

}

class Objeto(var nombre: String) : Serializable {

    var peso: Int = 0
    var valor: Int = 0
    var vida: Int = 0

    constructor(nombre: String, valor: Int) : this(nombre) {
        this.valor = valor
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

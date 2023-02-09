package com.example.ejercicio11

import java.io.Serializable

class Jugador(
    var nombre: String,
    var raza: String,
    var clase: String)
    : Serializable {

    //inicializar el monedero del personaje usando un hashmap con las monedas de 1,5,10,25 y 100
    var monedero: HashMap<Int, Int> = HashMap()
    var defensa: Int = (1..5).random()
    var fuerza: Int = (10..15).random()
    var vida: Int = 200
    var vida_max = 200
    var tamanyoMochila: Int = 100
    lateinit var mochila: MutableList<Objeto>
    var sumPeso = 0
    var sumValor = 0

    init {
        this.monedero[1] = 0
        this.monedero[5] = 0
        this.monedero[10] = 0
        this.monedero[25] = 2
        this.monedero[100] = 2
    }


    //Funcion para añadir monedas al monedero si se vende un objeto
    fun venderObjeto(objeto: Objeto) {
        var monedas = objeto.valor
        while (monedas > 0) {
            if (monedas >= 100) {
                monedero[100] = monedero[100]!! + 1
                monedas -= 100
            } else if (monedas >= 25) {
                monedero[25] = monedero[25]!! + 1
                monedas -= 25
            } else if (monedas >= 10) {
                monedero[10] = monedero[10]!! + 1
                monedas -= 10
            } else if (monedas >= 5) {
                monedero[5] = monedero[5]!! + 1
                monedas -= 5
            } else if (monedas >= 1) {
                monedero[1] = monedero[1]!! + 1
                monedas -= 1
            }
        }
    }




}


//funcion para pasar el monedero del jugador a un Int

fun monederoToInt(monedero: HashMap<Int, Int>): Int {
    var monedas = 0
    monedero.forEach { (moneda, cantidad) ->
        monedas += moneda * cantidad
    }
    return monedas
}

//funcion para pasar el Int del monedero a un HashMap

fun IntToMonedero(monedas: Int): HashMap<Int, Int> {
    var monedero: HashMap<Int, Int> = HashMap()
    monedero[1] = 0
    monedero[5] = 0
    monedero[10] = 0
    monedero[25] = 0
    monedero[100] = 0
    var valor = monedas
    var monedasInt = 0
    while (valor > 0) {
        if (valor >= 100) {
            monedasInt = valor / 100
            monedero[100] = monedero[100]!! + monedasInt
            valor -= monedasInt * 100
        }
        if (valor >= 25) {
            monedasInt = valor / 25
            monedero[25] = monedero[25]!! + monedasInt
            valor -= monedasInt * 25
        }
        if (valor >= 10) {
            monedasInt = valor / 10
            monedero[10] = monedero[10]!! + monedasInt
            valor -= monedasInt * 10
        }
        if (valor >= 5) {
            monedasInt = valor / 5
            monedero[5] = monedero[5]!! + monedasInt
            valor -= monedasInt * 5
        }
        if (valor >= 1) {
            monedasInt = valor / 1
            monedero[1] = monedero[1]!! + monedasInt
            valor -= monedasInt * 1
        }
    }
    return monedero
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

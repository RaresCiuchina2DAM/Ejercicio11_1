package com.example.ejercicio11

class Jugador : java.io.Serializable
    {
        var raza: String
        var clase: String
        var nombre: String
        var defensa: Int = (1..5).random()
        var fuerza: Int = (10..15).random()
        var vida: Int = 200
        lateinit var monedero: HashMap<String, Int>
        var tamanyoMochila: Int = 100
        lateinit var mochila: ArrayList<Objeto>
        var sumPeso = 0
        var sumValor = 0

        constructor(nombre: String, raza: String, clase: String) {
            this.nombre = nombre
            this.raza = raza
            this.clase = clase

        }
    }

class Objeto {

    var peso: Int = 0
    var valor: Int = 0
    var vida: Int = 0
    var nombre: String


    constructor (nombre: String) {
        this.nombre = nombre
    }
    init {

        vida = 20
        peso = 5
        valor = 10
    }

}

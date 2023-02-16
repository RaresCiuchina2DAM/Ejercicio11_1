package com.example.ejercicio11

interface Intefaz {
    fun hablarConJugador(texto: String, jugador: Jugador): String
    fun cifradoCesar(texto: String, codigo: Int): String
    fun hablar(personajeMercader: Jugador)
    fun comunicacion(texto: String, jugador: Jugador): String
}
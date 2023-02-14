package com.example.ejercicio11

import java.util.*

//recoger datos del usuario



fun comunicacion(respuesta: String, jugador: Jugador): String {


    return if (esMayuscula(respuesta)) {
        "¡${jugador.nombre} dice: ${respuesta.uppercase(Locale.getDefault())}!"
    } else {
        "¡${jugador.nombre} dice: ${respuesta.lowercase(Locale.getDefault())}!"
    }
}


//FUNCIONES DE COMUNICACIÓN
fun esMayuscula(s: String): Boolean {
    return s == s.uppercase(Locale.getDefault())
}

//FUNCIONES HABLAR CON EL USUARIO
fun hablarConJugador(texto: String, jugador: Jugador): String {

    if (jugador.raza.equals("Humano", true)) {
        return comunicacion(texto, jugador)
    } else if (jugador.raza.equals("Elfo", true)) {
        return cifradoCesar(comunicacion(texto, jugador), 13)
    } else if (jugador.raza.equals("Enano", true)) {
        return comunicacion(texto, jugador).lowercase()
    } else if (jugador.raza.equals("Goblin", true)) {
        return cifradoCesar(comunicacion(texto, jugador), 8)
    } else return "a"
}

fun cifradoCesar(texto: String, codigo: Int): String {
    var texto1 = texto.lowercase()
    var codigo = codigo
    val cifrado = StringBuilder()
    codigo %= 26
    for (i in texto1.indices) {
        if (texto1[i] in 'a'..'z') {
            if (texto1[i].code + codigo > 'z'.code) {
                cifrado.append((texto1[i].code + codigo - 26).toChar())
            } else {
                cifrado.append((texto1[i].code + codigo).toChar())
            }
        }
    }
    return cifrado.toString()
}

fun hablar(personajeMercader: Jugador) {
    var respuesta = ""
    println("¿Que quieres decirle?")
    respuesta = readLine()!!
    while (respuesta.lowercase() != "Adios".lowercase()) {
        hablarConJugador(respuesta, personajeMercader)
        println("¿Que quieres decirle?")
        respuesta = readLine()!!
    }
}

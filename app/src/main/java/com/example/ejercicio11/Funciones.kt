package com.example.ejercicio11

import java.util.*


public fun esMayuscula(s: String): Boolean {
    return s == s.uppercase(Locale.getDefault())
}

public fun hablarConJugador(texto: String, jugador: Jugador): String {

    if (jugador.raza.equals("Humano", true)) {
        return texto
    } else if (jugador.raza.equals("Elfo", true)) {
        return cifradoCesar(texto, 13)
    } else if (jugador.raza.equals("Enano", true)) {
        return cifradoCesar(texto.lowercase(), 8)
    } else if (jugador.raza.equals("Goblin", true)) {
        return cifradoCesar(texto, 12)
    } else return "a"
}

fun comunicacion(
    jugador: Jugador,
    respuesta: String
): String {

    when (jugador.edad) {
        in 12..22 -> {
            if (esMayuscula(respuesta) &&
                respuesta.get(index = 0) == '¿' &&
                respuesta.get(index = respuesta.length - 1) == '?'
            ) {
                return "Tranqui, se lo que hago"

            } else
                if (esMayuscula(respuesta)) {
                    return "Eh , relajate"
                } else if (!esMayuscula(respuesta) && respuesta.get(index = 0) == '¿' && respuesta.get(
                        index = respuesta.length - 1
                    ) == '?'
                ) {
                    return "De lujo"
                } else if (respuesta == jugador.nombre) {
                    return "¿Que pasa?"
                } else return "Yo que sé"
        }

        in 22..65 ->
            if (esMayuscula(respuesta) &&
                respuesta.get(index = 0) == '¿' &&
                respuesta.get(index = respuesta.length - 1) == '?'
            ) {
                return "Estoy buscando la mejor solución"
            } else
                if (esMayuscula(respuesta)) {
                    return "Eh , relajate"
                } else if (!esMayuscula(respuesta) && respuesta.get(index = 0) == '¿' && respuesta.get(
                        index = respuesta.length - 1
                    ) == '?'
                ) {
                    return "En la flor de la vida,\n pero me empieza a doler la espalda"
                } else if (respuesta == jugador.nombre) {
                    return "¿Necesitas algo?\""
                } else return "En mis tiempos esto no pasaba"

        else ->
            if (esMayuscula(respuesta) &&
                (respuesta.get(index = 0) == '¿') &&
                (respuesta.get(index = respuesta.length - 1) == '?')
            ) {
                return "Que no te escucho!"
            } else if (respuesta.equals("¿Como estas?")) {
                return "No me puedo mover"
            } else if (esMayuscula(respuesta)) {
                return "Háblame más alto que no te escucho"
            } else if (respuesta == jugador.nombre) {
                return "Las 5 de la tarde"
            } else return "En mis tiempos esto no pasaba"

    }
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

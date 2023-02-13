package com.example.ejercicio11

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityDialogFlowBinding
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.*
import java.util.*


const val USUARIO = 0
const val BOT = 1
const val ENTRADA_DE_VOZ = 2

class DialogFlow : AppCompatActivity() {

    private val uuid = UUID.randomUUID().toString()
    private var cliente: SessionsClient? = null
    private var sesion: SessionName? = null
    private var asistentevoz: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityDialogFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        //recoger el objeto jugador que se ha pasado por el intent
        val jugador = intent.getSerializableExtra("jugador") as Jugador


        binding.scrollChat.post {
            binding.scrollChat.fullScroll(binding.scrollChat.FOCUS_DOWN)

        }

        //caja para escribir los mensajes
        binding.cajadetexto.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {

                        // Llamamos al método enviarMensaje() el cual crearemos más adelante
                        enviarMensaje(enviar)
                        true
                    }
                    else -> {
                    }
                }
            }
            false
        }


        //Pasamos un setOnClickListener al botón de enviar llamando al método enviarMensaje()
        binding.enviar.setOnClickListener(this::enviarMensaje)
        //Pasamos un setOnClickListener al botón de enviar llamando al método enviarMensaje()
        binding.microfono.setOnClickListener(this::enviarMensajeMicrofono)

        iniciarAsistente()
        iniciarAsistenteVoz()
    }

    // Función para iniciar el asistente
    private fun iniciarAsistente() {
        try {
            //Este es el archivo de configuracion de la cuenta de DialogFlow
            val config = resources.openRawResource(R.raw.credenciales)
            //Aqui leeremos las credenciales
            val credenciales = GoogleCredentials.fromStream(config)
            //Aqui leemos el projectId que se encuentra en el archivo de las credenciales
            val projectId = (credenciales as ServiceAccountCredentials).projectId
            //Aqui construimos una configuracion para acceder al servicio de DialogFlow
            val generarConfiguracion = SessionsSettings.newBuilder()

            //Aqui configuramos las sesiones que vamos a usar en nuestra app
            val configuracionDeLasSesiones =
                generarConfiguracion.setCredentialsProvider(
                    (FixedCredentialsProvider.create(
                        credenciales
                    ))
                ).build()

            cliente = SessionsClient.create(configuracionDeLasSesiones)
            sesion = SessionName.of(projectId, uuid)

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    /**
     * Función para Iniciar el asistente de voz
     */
    private fun iniciarAsistenteVoz() {
        asistentevoz = TextToSpeech(
            applicationContext
        ) { status ->
            if (status != TextToSpeech.ERROR) {
                asistentevoz?.language = Locale("es")
            }
        }

    }

    /**
     * Función para enviar el mensaje al bot
     * @param view Es la vista que se le pasa al método
     */

    private fun enviarMensaje(view: View) {

        //obtenemos el binding de la vista y lo guardamos en una variable
        val binding = ActivityDialogFlowBinding.inflate(layoutInflater)
        //recoger de la vista view el texto que se ha escrito en la caja de texto
        val mensaje = binding.cajadetexto.text.toString()

        //si el usuario no ha escrito nada, y presio
        if (mensaje.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "No has escrito nada", Toast.LENGTH_SHORT).show()
        }
        //si el usuario ha escrito algo, llamamos al metodo agregar texto
        else {
            agregarTexto(mensaje, USUARIO)
            //limpiamos la caja de texto
            binding.cajadetexto.setText("")
            //Enviamos la consulta al bot
            val enviarConsulta = QueryInput.newBuilder().setText(
                TextInput.newBuilder().setText(mensaje).setLanguageCode("es")
            ).build()
            solicitarTarea(this, sesion!!, cliente!!, enviarConsulta)

        }


    }

    private fun enviarMensajeMicrofono(view: View) {

        //Intentamos reconocer la voz del usuario
        val intento = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        //Definimos los modelos de reconocimiento de voz
        intento.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        //Le diremos qe haga el reconocimiento de voz en el idioma local de la app
        intento.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        //Si el usuario no habla, le diremos que hable
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora")

        //Cuando chequeamos to do esto, enviamos el audio al bot
        try {
            startActivityForResult(intento, ENTRADA_DE_VOZ)
            // Si no se puede reconocer la voz, mostramos un mensaje de error
        } catch (e: Exception) {
            Toast.makeText(this, "Este mensaje de voz, no es admitido", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Función para agregar el texto al chat
     * @param mensaje Es el mensaje que se le pasa al método
     * @param tipo Es el tipo de mensaje que se le pasa al método
     */
    private fun agregarTexto(mensaje: String, type: Int) {


        //Colocamos el FrameLayout en la variable Fram
        var fram = FrameLayout(this)
        when (type){
            USUARIO->fram = agregarTextoUsuario()
            BOT->fram = agregarTextoBot()
            else->fram = agregarTextoBot()
        }
        //si el usuario hace clic en la caja de texto
        fram.isFocusableInTouchMode = true
        //paamos el linear layout
        binding.linearChat.addView(fram)

        // Mostramos los textos de los mensajes en un TextView
        val textview = fram.findViewById<TextView>(R.id.msg_chat)
        textview.text(mensaje)


        // Si el usuario sale del modo escritura, ocultamos el teclado del dispositivo
        // El método 'ocultarTeclado()' lo crearemos más adelante
        configTeclado.ocultarTeclado(this)

        // Enfocamos el TextView Automáticamente
        fram.requestFocus()

        // Volvemos a cambiar el enfoque para editar el texto y continuar escribiendo
        binding.cajadetexto.requestFocus()

        // Si es un cliente el que envía un mensaje al Bot, cargamos el método 'TexToSpeech'
        // 'TexToSpeech' junto a otras métodos procesa los mensajes de voz que seran enviados al Bot
        if(type!= USUARIO) asistentevoz?.speak(mensaje,TextToSpeech.QUEUE_FLUSH,null,)
    }

    // Colocamos los mensajes del Usuario en el layout 'mensaje_usuario'

    fun agregarTextoUsuario(): FrameLayout {
        val inflater = LayoutInflater.from(this@DialogFlow)
        return inflater.inflate(R.layout.mensaje_usuario, null) as FrameLayout
    }

    // Colocamos los mensajes del Bot en el layout 'mensaje_bot'
    fun agregarTextoBot(): FrameLayout {
        val inflater = LayoutInflater.from(this@DialogFlow)
        return inflater.inflate(R.layout.mensaje_bot, null) as FrameLayout
    }

    // Hacemos uso del método 'DetectIntentResponse' de Dialogflow para devolver algunos mensajes al usuario
    fun validar(response: DetectIntentResponse?) {
        try {
            if (response != null) {

                // fulfillmentText retorna un String (Texto) al usuario en la pantalla
                // fulfillmentMessagesList (Objeto) retorna una lista de objetos
                var respuestaBot: String = ""

                if(response.queryResult.fulfillmentText==" ")
                    respuestaBot = response.queryResult.fulfillmentMessagesList[0].text.textList[0].toString()
                else
                    respuestaBot = response.queryResult.fulfillmentText

                // Pasamos el método agregarTexto()
                agregarTexto(respuestaBot, BOT)

            } else {
                // Mostramos un mensaje si el audio que envio el usuario no se entiende
                agregarTexto(getString(R.string.audio_no_se_entiende), BOT)
            }
        }catch (e:Exception){
            // Mostramos al usuario el texto 'Por Favor, ingresa un mensaje'
            agregarTexto(getString(R.string.ingresa_mensaje), BOT)
        }
    }





}



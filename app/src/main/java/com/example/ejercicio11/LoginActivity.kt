package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityLogin2Binding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("nombre")
        val apellidos = intent.getStringExtra("apellidos")

        Toast.makeText(this, "Bienvenido $nombre $apellidos \n por favor, inicie sesión con sus credenciales por seguridad", Toast.LENGTH_SHORT).show()

        binding.IniciarSesion.setOnClickListener {
            if (binding.username.text.toString().isNotEmpty() && binding.password.text.toString()
                    .isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Usuario logeado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Error al logear el usuario", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(this, "Ojo!, rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }



        binding.registro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }




}
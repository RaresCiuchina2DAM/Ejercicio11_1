package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.register.setOnClickListener {
            val usuario = binding.editTextTextPersonName3.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val nombre = binding.editTextTextPersonName.text.toString()
            val apellidos = binding.editTextTextPersonName2.text.toString()

            if (usuario.isNotEmpty() && password.isNotEmpty() && nombre.isNotEmpty() && apellidos.isNotEmpty()
            ) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(usuario, password)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Usuario registrado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            intent.putExtra("nombre", nombre)
                            intent.putExtra("apellidos", apellidos)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Error al registrar el usuario",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Ojo!, rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }


    }
}

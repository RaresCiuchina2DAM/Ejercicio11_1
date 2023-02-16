package com.example.ejercicio11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ejercicio11.databinding.ActivityLogin2Binding


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val contrasenya = intent.getStringExtra("password")
        val usuario = intent.getStringExtra("usuario")
        val nombre = intent.getStringExtra("nombre")
        val apellidos = intent.getStringExtra("apellidos")

        binding.IniciarSesion.isEnabled = usuario != null && contrasenya != null && nombre != null && apellidos != null
        binding.registro.isEnabled = usuario == null && contrasenya == null && nombre == null && apellidos == null

        binding.IniciarSesion.setOnClickListener {
            if (binding.username.text.toString() == "" || binding.password.text.toString() == "") {
                Toast.makeText(this, "Ojo!, rellena todos los campos", Toast.LENGTH_SHORT).show()
            } else if (binding.username.text.toString() == usuario && binding.password.text.toString() == contrasenya) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos ", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }




    }




}
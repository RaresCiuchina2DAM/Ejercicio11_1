package com.example.ejercicio11

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio11.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.register.setOnClickListener{

            val usuario = binding.editTextTextPersonName3.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val nombre = binding.editTextTextPersonName.text.toString()
            val apellidos = binding.editTextTextPersonName2.text.toString()
            //SI alguno de los campos está vacío, se muestra un mensaje de error
            if(usuario == password){
                Toast.makeText(this, "Ojo!, usuario y contraseña no pueden ser iguales", Toast.LENGTH_SHORT).show()
            }
            //Si la contraseña y el usuario son iguales, se muestra un mensaje de error
            else if(usuario.isEmpty() || password.isEmpty() || nombre.isEmpty() || apellidos.isEmpty()){
                Toast.makeText(this, "Ojo!, ellena todos los campos", Toast.LENGTH_SHORT).show()
            }
            //Si to do está correcto, se crea un intent y se envían los datos a la actividad de login
            else{
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("usuario", usuario)
                intent.putExtra("password", password)
                intent.putExtra("nombre", nombre)
                intent.putExtra("apellidos", apellidos)
                startActivity(intent)
            }

        }




    }
}

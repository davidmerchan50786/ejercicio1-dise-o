package com.example.ejercicio1

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class PagoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        // Pillamos las vistas
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val etTitular = findViewById<EditText>(R.id.etTitular)
        val etNumTarjeta = findViewById<EditText>(R.id.etNumTarjeta)
        val etMes = findViewById<EditText>(R.id.etMes)
        val etAnio = findViewById<EditText>(R.id.etAnio)
        val etCVV = findViewById<EditText>(R.id.etCVV)
        val btnPagar = findViewById<Button>(R.id.bPagar)

        // Mostramos el resumen que nos han pasado
        val extras = intent.extras
        if (extras != null) {
            tvResultado.text = extras.getString("resumen")
        }

        btnPagar.setOnClickListener {
            // Quitamos los colores rojos de antes
            etTitular.setBackgroundColor(Color.TRANSPARENT)
            etNumTarjeta.setBackgroundColor(Color.TRANSPARENT)
            etMes.setBackgroundColor(Color.TRANSPARENT)
            etAnio.setBackgroundColor(Color.TRANSPARENT)
            etCVV.setBackgroundColor(Color.TRANSPARENT)

            var todoOk = true

            // Comprobamos el titular
            if (etTitular.text.toString().trim().isEmpty()) {
                etTitular.setBackgroundColor(Color.RED)
                todoOk = false
            }

            // La tarjeta tiene que tener 16 numeros
            if (etNumTarjeta.text.toString().length != 16) {
                etNumTarjeta.setBackgroundColor(Color.RED)
                todoOk = false
            }

            // Comprobamos la fecha de caducidad
            val mes = etMes.text.toString().toIntOrNull() ?: 0
            val anio = etAnio.text.toString().toIntOrNull() ?: 0
            val hoy = Calendar.getInstance()
            val mesActual = hoy.get(Calendar.MONTH) + 1
            val anioActual = hoy.get(Calendar.YEAR)

            // El mes tiene que estar entre 1 y 12
            if (mes < 1 || mes > 12) {
                etMes.setBackgroundColor(Color.RED)
                todoOk = false
            }

            // La tarjeta no puede estar caducada
            if (anio < anioActual || (anio == anioActual && mes < mesActual)) {
                etAnio.setBackgroundColor(Color.RED)
                todoOk = false
            }

            // El CVV son 3 numeros
            if (etCVV.text.toString().length != 3) {
                etCVV.setBackgroundColor(Color.RED)
                todoOk = false
            }

            // Si todo bien mostramos mensaje
            if (todoOk) {
                Toast.makeText(this, "Pago realizado!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.ejercicio1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Precios de las bebidas
    private val PRECIO_VINO = 3.21
    private val PRECIO_CERVEZA = 0.62
    private val PRECIO_REDBULL = 1.25
    private val PRECIO_KAS_LIMON = 0.54
    private val PRECIO_KAS_NARANJA = 0.54
    private val PRECIO_COCACOLA = 0.57

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pillamos las vistas
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val chkMayorEdad = findViewById<CheckBox>(R.id.chkMayorEdad)
        val edVino = findViewById<EditText>(R.id.edVino)
        val edCerveza = findViewById<EditText>(R.id.edCerveza)
        val edRedBull = findViewById<EditText>(R.id.edRedBull)
        val edKasLimon = findViewById<EditText>(R.id.edKasLimon)
        val edKasNaranja = findViewById<EditText>(R.id.edKasNaranja)
        val edCocaCola = findViewById<EditText>(R.id.edCocaCola)
        val btnCalcular = findViewById<Button>(R.id.bCalcular)

        btnCalcular.setOnClickListener {
            // Comprobamos que haya puesto el nombre
            val nombre = etNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Pon tu nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cogemos las cantidades, si esta vacio ponemos 0
            val cantVino = edVino.text.toString().toIntOrNull() ?: 0
            val cantCerveza = edCerveza.text.toString().toIntOrNull() ?: 0
            val cantRedBull = edRedBull.text.toString().toIntOrNull() ?: 0
            val cantKasLimon = edKasLimon.text.toString().toIntOrNull() ?: 0
            val cantKasNaranja = edKasNaranja.text.toString().toIntOrNull() ?: 0
            val cantCocaCola = edCocaCola.text.toString().toIntOrNull() ?: 0

            // Si quiere alcohol tiene que ser mayor de edad
            if ((cantVino > 0 || cantCerveza > 0) && !chkMayorEdad.isChecked) {
                Toast.makeText(this, "Tienes que ser mayor de edad para comprar alcohol", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Calculamos el total
            val total = cantVino * PRECIO_VINO +
                    cantCerveza * PRECIO_CERVEZA +
                    cantRedBull * PRECIO_REDBULL +
                    cantKasLimon * PRECIO_KAS_LIMON +
                    cantKasNaranja * PRECIO_KAS_NARANJA +
                    cantCocaCola * PRECIO_COCACOLA

            // Montamos el resumen de la compra
            val resumen = StringBuilder()
            resumen.append("Resumen de compra:\n")
            if (cantVino > 0) resumen.append("- Vino x$cantVino: ${"%.2f".format(cantVino * PRECIO_VINO)}€\n")
            if (cantCerveza > 0) resumen.append("- Cerveza x$cantCerveza: ${"%.2f".format(cantCerveza * PRECIO_CERVEZA)}€\n")
            if (cantRedBull > 0) resumen.append("- Red Bull x$cantRedBull: ${"%.2f".format(cantRedBull * PRECIO_REDBULL)}€\n")
            if (cantKasLimon > 0) resumen.append("- Kas Limon x$cantKasLimon: ${"%.2f".format(cantKasLimon * PRECIO_KAS_LIMON)}€\n")
            if (cantKasNaranja > 0) resumen.append("- Kas Naranja x$cantKasNaranja: ${"%.2f".format(cantKasNaranja * PRECIO_KAS_NARANJA)}€\n")
            if (cantCocaCola > 0) resumen.append("- Coca Cola x$cantCocaCola: ${"%.2f".format(cantCocaCola * PRECIO_COCACOLA)}€\n")
            resumen.append("\nTotal: ${"%.2f".format(total)}€")

            // Abrimos la pantalla de pago y le pasamos los datos
            val intent = Intent(this, PagoActivity::class.java)
            intent.putExtra("resumen", resumen.toString())
            startActivity(intent)
        }
    }
}
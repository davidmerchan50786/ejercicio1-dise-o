package com.example.ejercicio1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Vinculamos vistas con variables. Si el ID no existe en el XML, esto fallará en compilación.
    private companion object {
        const val PRECIO_VINO = 3.21
        const val PRECIO_CERVEZA = 0.62
        const val PRECIO_REDBULL = 1.25
        const val PRECIO_KAS_LIMON = 0.54
        const val PRECIO_KAS_NARANJA = 0.54
        const val PRECIO_COCACOLA = 0.57
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vinculamos vistas con variables. Si el ID no existe en el XML, esto fallará en compilación.
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val chkMayorEdad = findViewById<CheckBox>(R.id.chkMayorEdad)
        val edVino = findViewById<EditText>(R.id.edVino)
        val edCerveza = findViewById<EditText>(R.id.edCerveza)
        val edRedBull = findViewById<EditText>(R.id.edRedBull)
        val edKasLimon = findViewById<EditText>(R.id.edKasLimon)
        val edKasNaranja = findViewById<EditText>(R.id.edKasNaranja)
        val edCocaCola = findViewById<EditText>(R.id.edCocaCola)
        val btnCalcular = findViewById<Button>(R.id.bCalcular)
        val tvResuelto = findViewById<TextView>(R.id.tvResuelto)

        btnCalcular.setOnClickListener {
            // Validar campos obligatorios, si falla, mostramos mensaje y paramos
            val nombre = etNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Nombre y apellidos son obligatorios", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // si el campo está vacío, asumimos 0 (evita crash por formato)
            val cantidadVino = edVino.text.toString().toIntOrNull() ?: 0
            val cantidadCerveza = edCerveza.text.toString().toIntOrNull() ?: 0
            val cantidadRedBull = edRedBull.text.toString().toIntOrNull() ?: 0
            val cantidadKasLimon = edKasLimon.text.toString().toIntOrNull() ?: 0
            val cantidadKasNaranja = edKasNaranja.text.toString().toIntOrNull() ?: 0
            val cantidadCocaCola = edCocaCola.text.toString().toIntOrNull() ?: 0

            // si compra vino o cerveza, debe ser mayor de edad
            val compraAlcohol = cantidadVino > 0 || cantidadCerveza > 0
            if (compraAlcohol && !chkMayorEdad.isChecked) {
                Toast.makeText(
                    this,
                    "Debe ser mayor de edad para comprar alcohol",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // calcular total
            val total = cantidadVino * PRECIO_VINO +
                    cantidadCerveza * PRECIO_CERVEZA +
                    cantidadRedBull * PRECIO_REDBULL +
                    cantidadKasLimon * PRECIO_KAS_LIMON +
                    cantidadKasNaranja * PRECIO_KAS_NARANJA +
                    cantidadCocaCola * PRECIO_COCACOLA


            val resumen = StringBuilder().apply {
                append("Resumen de compra:\n")
                if (cantidadVino > 0) append("- Vino x$cantidadVino: ${"%.2f".format(cantidadVino * PRECIO_VINO)}€\n")
                if (cantidadCerveza > 0) append(
                    "- Cerveza x$cantidadCerveza: ${
                        "%.2f".format(
                            cantidadCerveza * PRECIO_CERVEZA
                        )
                    }€\n"
                )
                if (cantidadRedBull > 0) append(
                    "- Red Bull x$cantidadRedBull: ${
                        "%.2f".format(
                            cantidadRedBull * PRECIO_REDBULL
                        )
                    }€\n"
                )
                if (cantidadKasLimon > 0) append(
                    "- Kas Limón x$cantidadKasLimon: ${
                        "%.2f".format(
                            cantidadKasLimon * PRECIO_KAS_LIMON
                        )
                    }€\n"
                )
                if (cantidadKasNaranja > 0) append(
                    "- Kas Naranja x$cantidadKasNaranja: ${
                        "%.2f".format(
                            cantidadKasNaranja * PRECIO_KAS_NARANJA
                        )
                    }€\n"
                )
                if (cantidadCocaCola > 0) append(
                    "- Coca Cola x$cantidadCocaCola: ${
                        "%.2f".format(
                            cantidadCocaCola * PRECIO_COCACOLA
                        )
                    }€\n"
                )
                append("\nTotal: ${"%.2f".format(total)}€")
            }.toString()


            // mostramos resultado en textview
            tvResuelto.text = resumen

        }
    }
}
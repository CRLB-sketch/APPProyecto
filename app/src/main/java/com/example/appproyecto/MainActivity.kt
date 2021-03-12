package com.example.appproyecto

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Main Activity </h2>
 *
 * Esta parte será donde se programar la pestaña principal.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // *************************************************************************************
        // Aquí comenzará el código ------------------------------------------------------------

        // -------------------------------------------------------------------------------------
        // Vamos a definir e instanciar los botones del menu principal
        // -------------------------------------------------------------------------------------
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)

        // -------------------------------------------------------------------------------------
        // Para entrar a las otras pestañas
        // -------------------------------------------------------------------------------------

        // 1 - Para entrar a Mapas para ver los centros de atención
        btn1.setOnClickListener {
            val intent = Intent(this, MapsAttentionCenters::class.java)
            startActivity(intent)
        }

        // 2 - Para entrar a ver las estadísticas
        btn2.setOnClickListener {
            val intent = Intent(this, Statistics::class.java)
            startActivity(intent)
        }

        // 3 - Para entrar a ver la pestaña de Contactos de Emergencia
        btn3.setOnClickListener {
            val intent = Intent(this, Contactos::class.java)
            startActivity(intent)
        }

        // 4 - Para entrar a ver el listado de noticias
        btn4.setOnClickListener {
            val intent = Intent(this, newsGT::class.java)
            startActivity(intent)
        }
    }

}
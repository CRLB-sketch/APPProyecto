/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Contactos </h2>
 *
 * Esta clase será para programar la actividad de contactos
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

package com.example.appproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.lang.Exception

class Contactos : AppCompatActivity() {

    var tvCampo1:TextView? = null
    var tvCampo2:TextView? = null
    var tvCampo3:TextView? = null
    var laImagen:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactos)

        // Aquí comenzará el código ------------------------------------------------------------

        tvCampo1 = findViewById(R.id.textViewNumber1)
        tvCampo2 = findViewById(R.id.text_title)
        tvCampo3 = findViewById(R.id.text_phone)
        laImagen = findViewById(R.id.imageView)

        if(Network.avalibleRed(this)){
            // Ejecutar solicitud
            requestHTTPVolley("http://gtpreviene.researchmobile.co:3000/api/information")
        }else{
            Toast.makeText(this,"No hay conexion", Toast.LENGTH_SHORT)
        }

    }

    private fun requestHTTPVolley(url:String) {
        val queue = Volley.newRequestQueue(this) // Buscar nueva cola

        // Formular solicitud
        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response ->
            try {
                Log.d("requestHTTPVolley", response)

                val gson = Gson()
                val testing = gson.fromJson(response, EmergencyContact::class.java)
                // Log.d("GSON", testing.phone)

                /*
                tvCampo1?.text = testing.whatsapp_phone
                tvCampo2?.text = testing.data?.size.toString()
                tvCampo3?.text = testing.data?.get(0)?.title
                // tvCampo3?.text = testing.data?.get(0)?.phone
                // tvCampo3?.text = testing.data?.get(0)?.icon
                Picasso.get().load(testing.data?.get(0)?.icon).into(laImagen)*/

            }catch (e: Exception){

            }
        }, Response.ErrorListener {  })

        queue.add(request) // Añadirlo a mi cola
    }
}
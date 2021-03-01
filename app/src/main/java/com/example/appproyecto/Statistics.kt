package com.example.appproyecto

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Statics </h2>
 *
 * Esta parte será donde se programará la parte de las estadísticas de Guatemala.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.retrofitcustom.Network
import com.example.appproyecto.retrofitcustom.Utils
import com.example.appproyecto.statistics.Information
import com.google.gson.Gson
import java.lang.Exception

class Statistics : AppCompatActivity() {

    var country:TextView? = null
    var confirmed:TextView? = null
    var deads:TextView? = null
    var recovers:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        // Aquí comenzará el código
        country = findViewById(R.id.txt_country)
        confirmed = findViewById(R.id.txt_confirmed)
        deads = findViewById(R.id.txt_dead)
        recovers = findViewById(R.id.txt_recover)

        // Para revisar la conexión a internet
        if(Network.avalibleRed(this)){
            requestStatistics(Utils.URL_STATISTICS)
        }else{
            Toast.makeText(this,"No hay conexion", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestStatistics(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("requestContacts", response)

                val gson = Gson()
                val information = gson.fromJson(response, Information::class.java)
                Log.d("GSON", information.country)

                // Vamos a empezar a instanciar la información obtenida
                country?.text = information.country
                confirmed?.text = "Casos Confirmados: " + information.confirmed.toString()
                deads?.text = "Muertos: " + information.deaths.toString()
                recovers?.text = "Recuperados: " + information.recovered.toString()

            }catch (e: Exception){
                Toast.makeText(this,"Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, { })

        queue.add(request)
    }

}
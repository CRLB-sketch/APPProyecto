package com.example.appproyecto.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.R
import com.example.appproyecto.retrofitcustom.Utils
import com.google.gson.Gson

class newsDetail : AppCompatActivity() {

    var the_title: TextView? = null
    var the_image: ImageView? = null
    var the_description: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        the_title = findViewById(R.id.tvTittleDetail)
        the_image = findViewById(R.id.ivImageDetail)
        the_description = findViewById(R.id.tvContentDetail)

        // apiSolicitud(Utils.URL_NOTICES_LIST)

        val the_detail = intent.getStringExtra("com.example.appproyecto.news.newsadapter.NEWSOBJ")
        Toast.makeText(this,"Prueba: " + the_detail, Toast.LENGTH_SHORT).show()

    }

    private fun apiSolicitud(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("apiSolicitude", response)

                // Se creará el objeto, en este caso será toda la información de las noticias
                val gson = Gson()
                val newsArray = gson.fromJson(response, newJson::class.java)



            }catch (e: Exception){
                Toast.makeText(this,"Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, { })

        queue.add(request)
    }

}
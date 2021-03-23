package com.example.appproyecto.news
/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Statics </h2>
 *
 * Clase de la vista al abrir una noticia
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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.R
import com.example.appproyecto.retrofitcustom.Utils
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class newsDetail : AppCompatActivity() {

    /***
     * Estás variables son el contenido de las noticias
     * the_tile: título
     * the_image: la imagen a mostrar
     * the_description: el cuerpo de la noticia
     */
    var the_title: TextView? = null
    var the_image: ImageView? = null
    var the_description: TextView? = null

    /***
     * Estado activo del detalle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        //los cargamos con el id del layout
        the_title = findViewById(R.id.tvTittleDetail)
        the_image = findViewById(R.id.ivImageDetail)
        the_description = findViewById(R.id.tvContentDetail)

        val the_detail = intent.getStringExtra("com.example.appproyecto.news.newsadapter.NEWSOBJ")

        apiSolicitud(Utils.URL_NOTICES_LIST, the_detail!!.toInt()-1)

        if(the_detail.toInt() == 0){
            the_title?.text = "APP UVG"
            Picasso.get().load("https://www.uvg.edu.gt/wp-content/uploads/socialshare-logo.jpg").into(the_image!!)
            the_description?.text = "ULTIMA NOTICIA: APP Creada por:\nCristian Laynez y Elean Rivas"
        }
    }

    /***
     * Solicitud de la api
     * @see carga de archivos de la Api o error al conectar
     * @param url de la api, posición de la noticia
     */
    private fun apiSolicitud(url:String, index:Int){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("apiSolicitude", response)

                // Se creará el objeto, en este caso será toda la información de las noticias
                val gson = Gson()
                val newsArray = gson.fromJson(response, newJson::class.java)

                //carga de archivos desde la api
                the_title?.text = newsArray.get(index).title
                Picasso.get().load(newsArray.get(index).image).into(the_image!!)
                the_description?.text = newsArray.get(index).detail

            }catch (e: Exception){
                if(index != 0){
                    Toast.makeText(this,"Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
                }
            }
        }, { })

        queue.add(request)
    }

}
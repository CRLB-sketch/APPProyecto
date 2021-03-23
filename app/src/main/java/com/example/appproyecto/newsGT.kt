package com.example.appproyecto
/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Noticias Main </h2>
 *
 * Main Class de las noticias.
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
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.news.ClickListener
import com.example.appproyecto.news.NewsObj
import com.example.appproyecto.news.newsAdapter
import kotlinx.android.synthetic.main.activity_news_g_t.*
import kotlinx.android.synthetic.main.template_contact.view.*
import com.example.appproyecto.news.newJsonItem as newJsonItem
import com.example.appproyecto.news.newJson as array
import com.example.appproyecto.retrofitcustom.Network
import com.example.appproyecto.retrofitcustom.Utils
import com.android.volley.Request
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.news.newsDetail
import com.google.gson.Gson
import java.net.URL

class newsGT : AppCompatActivity() {

    var list: RecyclerView? = null //Recycler viewer para la lista de noticias
    var adapter: newsAdapter? = null //Adapter para el funcionamiento de la lista
    var layourManeger: RecyclerView.LayoutManager? = null //Vista del recyclerViewer

    val TAG = "com.example.appproyecto.news.newsadapter.NEWSOBJ" //Ruta del objeto de noticias

    // ArrayList para agregar las noticias
    var the_notices = ArrayList<NewsObj>()

    /***
     * Estado activo de las noticias
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_g_t)

        if(Network.avalibleRed(this)){

            apiSolicitude(Utils.URL_NOTICES_LIST)

        }else{
            Toast.makeText(this,"No hay conexion", Toast.LENGTH_SHORT).show()
        }

        // Para dar click
        adapter = newsAdapter(the_notices, object:ClickListener {
            override fun onClick(view: View, index: Int) {
                seeDetail(index)

                // refreshLayout()
            }
        })

        list?.adapter = adapter
    }

    /***
     * Método de apoyo para el recyclerviewer
     * @param
     * @see vista layout
     */
    private fun refreshLayout(){
        // Definir e instanciar la lista
        list = findViewById(R.id.rvNewsGt)
        list?.setHasFixedSize(true)

        layourManeger = LinearLayoutManager(this)
        list?.layoutManager = layourManeger
    }

    /***
     * Verifica y carga la appi necesaria para la solicitud de información
     * @see carga de la api
     * @param url de la api
     */
    private fun apiSolicitude(url:String) {
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, {
                response ->
            try {
                Log.d("apiSolicitude", response)

                // Se creará el objeto, en este caso será toda la información de las noticias
                val gson = Gson()
                val newsArray = gson.fromJson(response, array::class.java)

                var the_size: Int = newsArray.size
                var the_index: Int = the_size - 1

                // Para agregar las noticias
                the_notices.add(
                        NewsObj( // Objeto de prueba
                                "UVG",
                                "https://www.uvg.edu.gt/wp-content/uploads/socialshare-logo.jpg",
                                "Hecho por: Cristian Laynez y Elean Rivas"))

                for(i in 0..the_index){
                    the_notices.add(
                            NewsObj(
                            newsArray.get(i).title,
                            newsArray.get(i).image,
                            newsArray.get(i).detail)
                    )

                }

            refreshLayout()

            }catch (e: Exception){
                Toast.makeText(this,"Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, { })

        queue.add(request)
        refreshLayout()
    }

    /***
     * Nos permite ingresar a la noticia y ver todos los detalles
     * @param posicion de la noticia
     * @see inicia la noticia
     */
    private fun seeDetail(index: Int){
        val intent = Intent(this, newsDetail::class.java)
        intent.putExtra(TAG, index.toString())
        startActivity(intent)
    }

}
package com.example.appproyecto

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
import com.google.gson.Gson
import java.net.URL

class newsGT : AppCompatActivity() {

    var list: RecyclerView? = null
    var adapter: newsAdapter? = null
    var layourManeger: RecyclerView.LayoutManager? = null
    // ArrayList para agregar las noticias
    var the_notices = ArrayList<NewsObj>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_g_t)



        if(Network.avalibleRed(this)){

        }else{
            Toast.makeText(this,"No hay conexion", Toast.LENGTH_SHORT).show()
        }
        /*
        // Para agregar las noticias
        the_notices.add(
                NewsObj( // Objeto de prueba
                        "Pérdidas en el sector textil guatemalteco ascienden a los US\$400 millones",
                        "http://gtpreviene.researchmobile.co:3000/uploads/2ij5og5sq62uidkz_maquila.jpg",
                        "<p>Las estimaciones preliminares dan cuenta de que las pérdidas ascienden a US$403 millones (unos Q3 mil 103 millones) por la paralización de trabajo en las plantas y en los compradores, ya que los clientes en los Estados Unidos han requerido suspender los pedidos de las prendas de vestir.<br/></p><p>El reporte se obtuvo con base a una encuesta a los agremiados de la Comisión de Vestuario y Textil (Vestex), adscrito a la Asociación Guatemalteca de Exportadores (Agexport), en la cual se preguntó a las empresas sobre la producción de las prendas luego de las medidas adoptadas el pasado 16 de marzo en Guatemala, así como la crisis sanitaria en los Estados Unidos que es el principal mercado a donde se despacha el producto.<br/></p><p>“Estados Unidos está en un paro total, eso representa pérdida y las cancelaciones van por los US$400 millones”, precisó Alejandro Ceballos, presidente de Vestex quien dijo que la situación es similar para Honduras, Nicaragua y El Salvador donde se manufactura ropa, pero que adquieren productos que se fabrican en Guatemala.<br/></p>"
                ))*/

        // Definir e instanciar la lista
        list = findViewById(R.id.rvNewsGt)
        list?.setHasFixedSize(true)

        layourManeger = LinearLayoutManager(this)
        list?.layoutManager = layourManeger

        // Para dar click
        adapter = newsAdapter(the_notices, object:ClickListener {
            override fun onClick(view: View, index: Int) {
                // Toast.makeText(applicationContext, "Prueba: " + index, Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, "Prueba", Toast.LENGTH_SHORT).show()
            }
        })

        list?.adapter = adapter
    }

    private fun apiSolicitude(url:String){
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, {
                response ->
            try {
                Log.d("", response)

                // Se creará el objeto, en este caso será toda la información de las noticias
                val gson = Gson()
                val newsArray = gson.fromJson(response, array::class.java)
                var the_size: Int = newsArray.size
                var the_index: Int = the_size - 1

                for(i in 0..the_index){
                    the_notices.add(NewsObj(newsArray.get(i).title, newsArray.get(i).image, newsArray.get(i).detail))
                }

            }catch (e: Exception){
                Toast.makeText(this,"Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, { })
        queue.add(request)


    }



}
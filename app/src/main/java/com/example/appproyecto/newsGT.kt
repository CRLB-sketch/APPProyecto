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

class newsGT : AppCompatActivity() {

    var list: RecyclerView? = null
    var adapter: newsAdapter? = null
    var layourManeger: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_g_t)

        // ArrayList para agregar las noticias
        val the_notices = ArrayList<NewsObj>()

        // Para agregar las noticias
        the_notices.add(
                NewsObj( // Objeto de prueba
                        "Pérdidas en el sector textil guatemalteco ascienden a los US\$400 millones",
                        "http://gtpreviene.researchmobile.co:3000/uploads/2ij5og5sq62uidkz_maquila.jpg",
                        "<p>Las estimaciones preliminares dan cuenta de que las pérdidas ascienden a US$403 millones (unos Q3 mil 103 millones) por la paralización de trabajo en las plantas y en los compradores, ya que los clientes en los Estados Unidos han requerido suspender los pedidos de las prendas de vestir.<br/></p><p>El reporte se obtuvo con base a una encuesta a los agremiados de la Comisión de Vestuario y Textil (Vestex), adscrito a la Asociación Guatemalteca de Exportadores (Agexport), en la cual se preguntó a las empresas sobre la producción de las prendas luego de las medidas adoptadas el pasado 16 de marzo en Guatemala, así como la crisis sanitaria en los Estados Unidos que es el principal mercado a donde se despacha el producto.<br/></p><p>“Estados Unidos está en un paro total, eso representa pérdida y las cancelaciones van por los US$400 millones”, precisó Alejandro Ceballos, presidente de Vestex quien dijo que la situación es similar para Honduras, Nicaragua y El Salvador donde se manufactura ropa, pero que adquieren productos que se fabrican en Guatemala.<br/></p>"
                ))

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

}
package com.example.appproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appproyecto.news.newsAdapter
import kotlinx.android.synthetic.main.activity_news_g_t.*
import kotlinx.android.synthetic.main.template_contact.view.*
import com.example.appproyecto.news.newJsonItem as newJsonItem
import com.example.appproyecto.news.newJson as array

class newsGT : AppCompatActivity() {

    val news = listOf(
            newJsonItem(1,0,"hoy","fecha",0,"imagen","Noticia","Fecha","Titular")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_g_t)
        initRecycler()
    }

    fun initRecycler(){
        rvNewsGt.layoutManager = LinearLayoutManager(this)
        //val adapter = newsAdapter(array)

    }
}
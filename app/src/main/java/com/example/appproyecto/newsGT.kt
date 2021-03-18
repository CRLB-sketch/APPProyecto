package com.example.appproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appproyecto.news.newJsonItem
import kotlinx.android.synthetic.main.template_contact.view.*

class newsGT : AppCompatActivity() {

    val news = listOf(
            newJsonItem(1,0,"hoy","fecha",0,"imagen","Noticia","Fecha","Titular")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_g_t)

    }
}
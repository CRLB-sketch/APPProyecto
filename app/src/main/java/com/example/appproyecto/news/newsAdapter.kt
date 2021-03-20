package com.example.appproyecto.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.R
import kotlinx.android.synthetic.main.item_new.view.*


class newsAdapter(val newJsonItem: List<newJsonItem>):RecyclerView.Adapter<newsAdapter.newsHolder>(){


    class newsHolder(val view: View):RecyclerView.ViewHolder(view){
        fun render(newitem:newJsonItem) {
            view.tvTitle.text = newitem.title
            view.tvDate.text = newitem.start
            view.tvContent.text = newitem.detail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return newsHolder(layoutInflater.inflate(R.layout.item_new, parent, false))
    }

    override fun onBindViewHolder(holder: newsHolder, position: Int) {
        holder.render(newJsonItem[position])
    }

    override fun getItemCount(): Int {
       return newJsonItem.size
    }

}
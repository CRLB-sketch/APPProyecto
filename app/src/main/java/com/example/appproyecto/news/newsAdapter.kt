package com.example.appproyecto.news

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class newsAdapter(val newJsonItem: List<newJsonItem>):RecyclerView.Adapter<newsAdapter.newsHolder>(){


    class newsHolder(val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: newsHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
       return newJsonItem.size
    }
}
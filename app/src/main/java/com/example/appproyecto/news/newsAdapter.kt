package com.example.appproyecto.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_new.view.*


class newsAdapter(items:ArrayList<NewsObj>, val listener: ClickListener): RecyclerView.Adapter<newsAdapter.NewsHolder>() {

    var items: ArrayList<NewsObj>? = null

    var viewHolder: NewsHolder? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val the_view = LayoutInflater.from(parent?.context).inflate(R.layout.item_new, parent, false)
        viewHolder = NewsHolder(the_view, listener)

        return viewHolder!!
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = items?.get(position)

        holder.title?.text = item?.title
        Picasso.get().load(item?.image).into(viewHolder?.image)
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    class NewsHolder(view: View, listener: ClickListener): RecyclerView.ViewHolder(view), View.OnClickListener {

        var view = view

        var title: TextView? = null
        var image: ImageView? = null

        var listener: ClickListener? = null

        init {
            title = view.findViewById(R.id.tvTitleM)
            image = view.findViewById(R.id.ivImageM)

            this.listener = listener

            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

    }

}
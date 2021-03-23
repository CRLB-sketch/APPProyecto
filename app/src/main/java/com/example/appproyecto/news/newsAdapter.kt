package com.example.appproyecto.news
/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Noticias adapter </h2>
 *
 * Adaptador para la lista de noticias
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/
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

    //Arraylist para almacenar las noticias
    var items: ArrayList<NewsObj>? = null

    //Holder para el recyclerviewer
    var viewHolder: NewsHolder? = null

    init {
        this.items = items
    }

    /***
     * Funcionamiento del viewHolder
     * @see vista listado de noticias
     * @param
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val the_view = LayoutInflater.from(parent?.context).inflate(R.layout.item_new, parent, false)
        viewHolder = NewsHolder(the_view, listener)

        return viewHolder!!
    }

    /***
     * Items del holder
     */
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = items?.get(position)

        holder.title?.text = item?.title
        Picasso.get().load(item?.image).into(viewHolder?.image)
    }

    /***
     * Cuenta la cantidad de items
     * @return tamaño de la lista
     */
    override fun getItemCount(): Int {
        return items?.count()!!
    }

    //Clase holder, para el item de las noticias
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

        /***
         * Funcion cuando hagan click en un objeto de la noticia
         * @see ventana de retroalimentación del click
         * @param vista a obtener
         */
        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

    }

}
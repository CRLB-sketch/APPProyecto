package com.example.appproyecto.emergencycontacts

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> AdapterCustom </h2>
 *
 * Esta clase nos ayudará a tener nuestro adaptador personalizado
 * para los contactos que se van a instanciar en la app.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.appproyecto.R
import com.squareup.picasso.Picasso

class AdapterCustom(var context:Context, items:ArrayList<Data>):BaseAdapter() {

    // --> Atributos
    var items:ArrayList<Data>? = null

    // --> Constructor
    init {
        this.items = items
    }

    // --> Métodos
    override fun getCount(): Int {
        return this.items?.count()!!
    }

    override fun getItem(position: Int): Any {
        return this.items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = null
        var view:View? = convertView

        if(view == null){
            // El template ya esta asociado a nuestra "celda"
            view = LayoutInflater.from(context).inflate(R.layout.template_contact, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            viewHolder = view.tag as? ViewHolder
        }

        val item = getItem(position) as Data

        viewHolder?.title?.text = item.title
        Picasso.get().load(item.icon).into(viewHolder?.icon)
        viewHolder?.phone?.text = item.phone

        return view!!
    }

    private class ViewHolder(view:View){
        var title:TextView? = null
        var icon: ImageView? = null
        var phone:TextView? = null

        init {
            title = view.findViewById(R.id.tvTitle)
            icon = view.findViewById(R.id.ivImage)
            phone = view.findViewById(R.id.tvPhone)
        }
    }

}
package com.example.appproyecto.news

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> ClickListener </h2>
 *
 * Método para dar click a la noticia que se quiere ver el detalle
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import android.view.View

interface ClickListener {

    fun onClick(view: View, index:Int)

}
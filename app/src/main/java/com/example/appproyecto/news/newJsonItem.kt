package com.example.appproyecto.news
/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Statics </h2>
 *
 * Esta parte será donde se programará la parte de las estadísticas de Guatemala.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/
data class newJsonItem(
    val active: Int,
    val category_id: Int,
    val detail: String,
    val end: String,
    val id: Int,
    val image: String,
    val name: String,
    val start: String,
    val title: String
)
package com.example.appproyecto.maps

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Center Item </h2>
 *
 * Esta clase representará un item de todos los centros
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

data class CenterItem(
    // --> Atributos
    val active: Int,
    val description: String,
    val id: Int,
    val latitude: String,
    val longitude: String,
    val name: String
)
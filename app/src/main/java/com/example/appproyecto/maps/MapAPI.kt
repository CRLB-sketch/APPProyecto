package com.example.appproyecto.maps

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> MapAPI </h2>
 *
 * Esta interface nos ayudará a obtener los datos correspendientes de la
 * api de las ubicaciones.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MapAPI {
    @GET("{id}")
    fun find(@Path("id") id: String?): Call<List<Maps?>?>?
}
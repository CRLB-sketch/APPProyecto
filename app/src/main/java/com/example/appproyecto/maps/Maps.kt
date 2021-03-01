package com.example.appproyecto.maps

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Maps </h2>
 *
 * Esta clase nos ayudará a obtener los datos correspondientes
 * de las ubicaciones.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Maps {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("active")
    @Expose
    var active: Int? = null
}
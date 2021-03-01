package com.example.appproyecto.statistics

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Information </h2>
 *
 * Esta clase representará todos los datos de las estadísticas
 * obtenidas de Guatemala.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

class Information {

    // --> Atributos
    var country:String = ""
    var confirmed:Int = 0
    var deaths:Int = 0
    var recovered:Int = 0

    // --> Constructor
    constructor(country:String, confirmed:Int, deaths:Int, recovered:Int){
        this.country = country
        this.confirmed = confirmed
        this.deaths = deaths
        this.recovered = recovered
    }

}
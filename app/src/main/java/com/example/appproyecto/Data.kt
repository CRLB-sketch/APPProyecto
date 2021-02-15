/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Data </h2>
 *
 * Esta clase es la que tendrá los atributos necesarios para tener los
 * datos completos del contacto de emergencia
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

package com.example.appproyecto

class Data {

    // --> Atributos
    var icon:String = ""
    var title:String = ""
    var phone:String = ""

    // --> Constructor
    constructor(title:String, phone:String, icon:String){
        this.icon = icon
        this.title = title
        this.phone = phone
    }
}
/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Emergency Contact </h2>
 *
 * Esta clase representará un contacto de emergencia
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

class EmergencyContact {

    // --> Atributos
    var whatsapp_phone:String = ""
    var data:ArrayList<Data>? = null

    // --> Constructor
    constructor(whatsapp_phone:String, data:ArrayList<Data>){
        this.whatsapp_phone = whatsapp_phone
        this.data = data
    }

}
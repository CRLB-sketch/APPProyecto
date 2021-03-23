package com.example.appproyecto.news
/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Objeto noticias </h2>
 *
 * objeto que toma la información que se muestra en el detalle de la noticia
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/
class NewsObj {

    // --> Atributos
    var title = ""
    var image = ""
    var detail = ""

    // --> Constructor
    constructor(title:String, image:String, detail:String){
        this.title = title
        this.image = image
        this.detail = detail
    }

}
package com.example.appproyecto.retrofitcustom

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Utils </h2>
 *
 * Esta clase nos dará los Urls que se utilizarán para obtener los datos
 * soliciatados o deseados.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

object Utils {
    @JvmField
    val URL_BASE = "http://gtpreviene.researchmobile.co:3000/api/"
    val URL_ATTENTION_CENTERS = "http://gtpreviene.researchmobile.co:3000/api/centres"
    val URL_STATISTICS = "http://gtpreviene.researchmobile.co:3000/api/covid"
    val URL_EMERGENCY_CONTACTS = "http://gtpreviene.researchmobile.co:3000/api/information"
    val URL_NOTICES_LIST = "http://gtpreviene.researchmobile.co:3000/api/news"
}
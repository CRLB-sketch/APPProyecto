package com.example.appproyecto.retrofitcustom

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Network </h2>
 *
 * Esta clase nos ayudará a verificar si hay red o no.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class Network {

    companion object {
        fun avalibleRed(activity: AppCompatActivity):Boolean{
            val connectivityManeger = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManeger.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

}
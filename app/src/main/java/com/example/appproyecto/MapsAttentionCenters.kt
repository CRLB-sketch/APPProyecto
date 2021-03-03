package com.example.appproyecto

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> MapsAttentionCenters </h2>
 *
 * Esta parte será donde se programar la pestaña del mapa
 * y también se obtendrán todas las ubicaciones necesarias.
 *
 * <p> Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.maps.Center
import com.example.appproyecto.retrofitcustom.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import java.lang.Exception

class MapsAttentionCenters : AppCompatActivity(), OnMapReadyCallback {

    var perms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_attention_centers)
        createFragment()
    }

    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap : GoogleMap) {
        map = googleMap
        createMarkers(Utils.URL_ATTENTION_CENTERS)
    }

    private fun createMarkers(url:String) {

        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("createMarkers", response)

                val gson = Gson()
                val center = gson.fromJson(response, Center::class.java)

                var the_size: Int = center.size
                var the_index: Int = the_size - 1

                // Para agregar todos los marcadores de los centros de salud
                for(i in 0..the_index){
                    val coordinates = LatLng(center.get(i).latitude.toDouble(), center.get(i).longitude.toDouble())
                    val marker = MarkerOptions().position(coordinates).title(center.get(i).name)
                    map.addMarker(marker)
                }

                // Posisionar la camara
                val guateCoordinates = LatLng(15.756965, -90.4155727)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(guateCoordinates, 7f), 4000, null)

            }catch (e: Exception){
                Toast.makeText(this,"Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, { })

        queue.add(request)
    }
}

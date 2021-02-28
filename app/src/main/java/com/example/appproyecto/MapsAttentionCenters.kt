package com.example.appproyecto

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.maps.Ubication
import com.example.appproyecto.retrofitcustom.Network
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
        createMarkers()
    }

    private fun createMarkers(){

        val coordinates = LatLng (14.60397982433268, -90.48922118080561)
        val marker = MarkerOptions().position(coordinates).title("UVG")

        // Añadir los marcadores
        map.addMarker(marker)

        // Para animar la camara
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null)

        // Para revisar la conexión a internet
        if(Network.avalibleRed(this)){
            // Si sale bien se llevará a cabo la solicitud
            requestUbicactions(Utils.URL_ATTENTION_CENTERS)

        }else{
            Toast.makeText(this,"No hay conexion", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestUbicactions(url:String){
        val queue = Volley.newRequestQueue(this)

        // Formular solicitud
        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("requestUbicactions", response)

                val gson = Gson()
                val the_map = gson.fromJson(response, Ubication::class.java)
                Log.d("GSON", the_map.toString())

                // Toast.makeText(this, "Nombre lugar: " + the_map.name, Toast.LENGTH_SHORT).show()

            } catch (e: Exception){
                Toast.makeText(this, "Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, { })

        queue.add(request)
    }
}
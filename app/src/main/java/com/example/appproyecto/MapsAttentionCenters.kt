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
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.maps.MapAPI
import com.example.appproyecto.maps.Maps
import com.example.appproyecto.retrofitcustom.Network
import com.example.appproyecto.retrofitcustom.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import pub.devrel.easypermissions.EasyPermissions
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
        createMarkers()
    }

    private fun createMarkers() {

        val retrofit = Retrofit.Builder().baseUrl(Utils.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mapAPI = retrofit.create(MapAPI::class.java)
        val call = mapAPI.find("centres")
        call?.enqueue(object : Callback<List<Maps>> {
            override fun onResponse(call: Call<List<Maps>>, response: Response<List<Maps>>) {
                try {

                    //  Toast.makeText(this@MapsAttentionCenters, "Listo", Toast.LENGTH_SHORT).show()

                    var position: LatLng? = null

                    for(local in response.body()!!){
                        position = LatLng(local.latitude?.toDouble()!!, local.longitude?.toDouble()!!)
                        map!!.addMarker(MarkerOptions().position(position).title(local.name))
                    }

                    map!!.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 8f))
                    map!!.setOnMarkerClickListener { marker ->
                        val latLng = marker.position
                        if (EasyPermissions.hasPermissions(this@MapsAttentionCenters, *perms)) {
                            val uri = "http://maps.google.com/maps?q=loc:" + latLng.latitude + "," + latLng.longitude + " (" + marker.title + ")"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                            intent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
                            intent.flags = Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
                            intent.data = Uri.parse(uri)
                            startActivity(intent)
                        }
                        true
                    }

                }catch (ex:Exception){
                    Toast.makeText(this@MapsAttentionCenters, ex.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Maps>>, t: Throwable) {
                Toast.makeText(this@MapsAttentionCenters, "Ocurrio un error :(", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

private fun <T> Call<T>?.enqueue(callback: Callback<List<Maps>>) { }

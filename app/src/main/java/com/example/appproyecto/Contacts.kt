package com.example.appproyecto

/**
 * <h1> Proyecto APP - Guate-Covidianos </h1>
 * <h2> Contactos </h2>
 *
 * Esta clase será para programar la actividad de contactos.
 *
 * <p>Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author [Cristian Laynez, Elean Rivas]
 * @version 1.0
 * @since 2020-Enero-19
 *
 **/

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.emergencycontacts.AdapterCustom
import com.example.appproyecto.emergencycontacts.Data
import com.example.appproyecto.emergencycontacts.EmergencyContact
import com.example.appproyecto.retrofitcustom.Network
import com.example.appproyecto.retrofitcustom.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_contacts.*
import java.lang.Exception


class Contactos : AppCompatActivity() {

    // --> Atributos Globales
    var list: ListView? = null

    val REQUEST_PHONE_CALL = 1
    var auxPhone: String = ""

    // --> Transformar en un objeto estático
    companion object {
        var emergencyContacts:ArrayList<Data>? = null
        var adapter: AdapterCustom? = null

        // Método para obtener el numero correcto
        fun getEContact(index:Int):Data{
            return adapter?.getItem(index) as Data
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        // Aquí comenzará el código ------------------------------------------------------------

        // setSupportActionBar(toolbar)

        // Para revisar la conexión a internet
        if(Network.avalibleRed(this)){
            // Si sale bien se llevará a cabo la solicitud
            requestContacts(Utils.URL_EMERGENCY_CONTACTS)

        }else{
            Toast.makeText(this,"No hay conexion", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * Este método obtendrá los datos de la api de los contactos de emergencía
     * y aparte se agregarán los contactos obtenidos de la api.
     *
     * @param url   Para solicitar el url de los contactos de emergencia
     * @see         Los contactos encontrados
     */
    private fun requestContacts(url:String) {
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("requestContacts", response)

                // Se creará el objeto, para este caso el contacto de emergencia
                val gson = Gson()
                val the_contact = gson.fromJson(response, EmergencyContact::class.java)

                // Se inicializará el ArrayList donde se almacenará los contactos de emergencia
                emergencyContacts = ArrayList()

                // Para obtener el tamaño de los contactos que estan en la api
                var the_size:Int = the_contact.data?.size?.toInt()!!
                var the_index:Int = the_size - 1

                // Esto es para agregar todos los contactos disponibles
                for(i in 0..the_index){
                    emergencyContacts?.add(Data( the_contact.data?.get(i)?.title.toString(),
                                                 the_contact.data?.get(i)?.phone.toString(),
                                                 the_contact.data?.get(i)?.icon.toString()))
                }

                // Para actualizar la lista y el adaptador personalizado
                list = findViewById<ListView>(R.id.the_list)
                adapter = AdapterCustom(this, emergencyContacts!!)

                // // Para hacer una llamada
                list?.setOnItemClickListener { parent, view, position, id ->

                    // Vamos a verificar si obtubimos el permiso o no
                    if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE) ,REQUEST_PHONE_CALL)
                    }else{
                        // Si se accedio el permiso esta en orden se procederá a realizar la llamada
                        val the_correct = getEContact(position)
                        auxPhone = the_correct.phone.toString()

                        makeCall()
                    }

                }

                list?.adapter = adapter

            }catch (e: Exception){ // Por sí ocurre algo inesperado
                Toast.makeText(this,"Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, {  })

        queue.add(request) // Añadirlo a mi cola
    }

    /**
     * Método para llevar a cabo la llamada del contacto seleccionado
     */
    private fun makeCall(){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + auxPhone)
        startActivity(callIntent)
    }

    /**
     * Método para pedir los permisos si es primera vez que se pide/abre el app
     *
     * @param requestCode   Codigo de la solicitud
     * @param permissions   Los permisos accedidos
     * @param grantResults  Tener verificado los resultados
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == REQUEST_PHONE_CALL)makeCall()
    }

    /**
     * Método para tener las opciones que se pueden llevar a cabo
     * Como por ejemplo buscar un contacto en específico
     *
     * @param menu  Este mismo menu
     * @return      El menu creado
     * @see         Toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_emergency_contact, menu)

        val serchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemSearch = menu?.findItem(R.id.searchView)
        val searchView = itemSearch?.actionView as SearchView

        searchView.setSearchableInfo(serchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar contacto..."

        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            // Nos servira para preparar los datos

        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                // Para filtrar los datos
                adapter?.filterInfo(newText!!)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // Tambien para filtrar la informacion
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Para tener un mejor control con el adaptador personalizado
     */
    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }
}
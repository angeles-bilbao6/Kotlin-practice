package com.example.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.Exception


class MainActivity : AppCompatActivity() , CompletedListener{

    lateinit var txtCity:TextView
    lateinit var txtStatus:TextView
    lateinit var txtTemperature:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCity = findViewById<TextView>(R.id.place)
        txtStatus =  findViewById<TextView>(R.id.status)
        txtTemperature =  findViewById<TextView>(R.id.grades)

        val city = intent.getStringExtra("city");

        if(Network.networkCheck(this))
        {
            requestHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id=" + city + "&appid=598146186dd1e0823d35270d9bb2ce03")
            //DownloadURL(this).execute("http://api.openweathermap.org/data/2.5/weather?id=3530597&appid=598146186dd1e0823d35270d9bb2ce03")
        }
        else
            Toast.makeText(this, "No", Toast.LENGTH_LONG).show()



    }

    private fun requestHTTPVolley(url: String) {
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, Response.Listener <String>{
                response ->
            try {
                Log.d("RESPONSE", response)
                var gson =  Gson()
                var result = gson.fromJson(response, City::class.java)

                txtCity.text = result.name
                txtStatus.text = result.weather?.get(0)?.description
                txtTemperature.text = (result.main?.temp?.minus(173)).toString() + "°"
            }
            catch (e: Exception)
            {
                Log.d("ERROR", e.toString())
            }
        }, Response.ErrorListener {  })
        queue.add(request)
    }


    override fun downloadCompleted(result: String) {
        Log.d("json", result)

    }

}

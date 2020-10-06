package com.example.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException
import java.lang.Exception

class ValidateHTTP : AppCompatActivity(), CompletedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_h_t_t_p)

        val button = findViewById<Button>(R.id.button)
        val buttonHTTP = findViewById<Button>(R.id.button2)
        val bVolley = findViewById<Button>(R.id.button3)
        val bOkHttp = findViewById<Button>(R.id.button4)
        button.setOnClickListener(View.OnClickListener {
            if(Network.networkCheck(this))
            {
                Toast.makeText(this, "there is", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
        })

        buttonHTTP.setOnClickListener(View.OnClickListener {
            if(Network.networkCheck(this))
            {
                DownloadURL(this).execute("https://www.google.com")
            }
            else
                Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
        })

        bVolley.setOnClickListener(View.OnClickListener {
            if(Network.networkCheck(this))
            {
                requestHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id=3530597&appid=598146186dd1e0823d35270d9bb2ce03")
            }
            else
                Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
        })
        bOkHttp.setOnClickListener(View.OnClickListener {
            if(Network.networkCheck(this))
            {
                requestOkHTTP("https://www.google.com")
            }
            else
                Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
        })
    }

    override fun downloadCompleted(result: String) {
        Log.d("DownloadCompleted", result);
    }
    //Volley
    private fun requestHTTPVolley(url:String)
    {
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, Response.Listener <String>{
            response ->
            try {
                Log.d("voolley", response)
            }
            catch (e:Exception)
            {

            }
        }, Response.ErrorListener {  })
        queue.add(request)
    }
    //OKHTTP
    private fun requestOkHTTP(url:String)
    {
        val client = OkHttpClient()
        val request = okhttp3.Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: okhttp3.Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call?, response: okhttp3.Response?) {
                val result = response?.body()?.string()
                Log.d("okHTTP", result)
            }
        })
    }
}

package com.example.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        val bMexico = findViewById<Button>(R.id.mexicoButton)
        val bSF = findViewById<Button>(R.id.sfButton)

        bMexico.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("city", getString(R.string.mexid))
            startActivity(intent)
        })
        bSF.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("city", getString(R.string.sfid))
            startActivity(intent)
        })
    }
}

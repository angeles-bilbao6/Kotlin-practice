package com.example.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class Fruits : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruits)

        var fruits:ArrayList<Fruit> = ArrayList()

        fruits.add(Fruit("Apple",R.drawable.apple_bg))
        fruits.add(Fruit("Banana",R.drawable.banana_bg))
        fruits.add(Fruit("Cherry",R.drawable.cherry_bg))
        fruits.add(Fruit("Orange",R.drawable.orange_bg))
        fruits.add(Fruit("Pear",R.drawable.pear_bg))
        fruits.add(Fruit("Plum",R.drawable.plum_bg))
        fruits.add(Fruit("Raspberry",R.drawable.raspberry_bg))
        fruits.add(Fruit("Strawberry",R.drawable.strawberry_bg))

        val list = findViewById<ListView>(R.id.list)
        val adapter = MyAdapter(this, R.layout.list_item, fruits)
        list.adapter = adapter
        list.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this, fruits.get(position).name, Toast.LENGTH_LONG).show()
        }
    }
}

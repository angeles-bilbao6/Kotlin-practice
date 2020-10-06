package com.example.clima

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(context: Context, layout: Int, fruits: ArrayList<Fruit>) : BaseAdapter() {
    var fruits: ArrayList<Fruit>? = null
    var context: Context? = null
    var layout:Int = 0

    init {
        this.fruits = fruits
        this.layout = layout
        this.context = context

    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder? = null
        var view: View? = convertView
        if(view == null)
        {
            view = LayoutInflater.from(this.context).inflate(R.layout.list_item, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else
        {
            viewHolder = view.tag as? ViewHolder
        }
        val item = getItem(position) as Fruit
        viewHolder?.img?.setImageResource(item.icon)
        viewHolder?.text?.text = item.name
        return view!!

    }

    override fun getItem(position: Int): Any {
        return fruits?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong() //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return fruits?.count()!!
    }
    private class ViewHolder(view: View)
    {
        var img: ImageView? = null
        var text: TextView? = null
        init {
            img = view.findViewById(R.id.img)
            text = view.findViewById(R.id.text)
        }
    }
}

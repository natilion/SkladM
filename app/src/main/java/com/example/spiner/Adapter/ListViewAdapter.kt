package com.example.spiner.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.spiner.Activity.SelectCabinet
import com.example.spiner.R
import com.example.spiner.models.Cabinet

class ListViewAdapter(val selectCabinet: SelectCabinet,
                      val Cabinets:List<Cabinet>):BaseAdapter(), View.OnClickListener {
    override fun getCount(): Int {
        return Cabinets.size
    }

    override fun getItem(position: Int): Any {
        return Cabinets[position]
    }

    override fun getItemId(position: Int): Long {
        return Cabinets[position].ID_Cabinet.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(selectCabinet).inflate(R.layout.list_item_2, parent, false)
        val id = view?.findViewById<TextView>(R.id.Id)
        val name = view?.findViewById<TextView>(R.id.Name)
        id?.text = Cabinets.get(position).ID_Cabinet.toString()
        name?.text = Cabinets.get(position).Name_Cabinet
        return view
    }

    override fun onClick(v: View) {
        val Cabinet = v.tag as Cabinet
    }
}
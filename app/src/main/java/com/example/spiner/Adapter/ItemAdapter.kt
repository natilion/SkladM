package com.example.spiner.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spiner.R
import com.example.spiner.models.Item
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(private val items: List<Item>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.IDItem.text = item.ID_Item.toString()
        holder.name.text = item.Name_Item
        holder.VC.text = item.Vendor_Code
        holder.Specification_Item.text = item.Specification_Item
        holder.About_Item.text = item.About_Item
        holder.User_ID.text = item.User_ID.toString()
        holder.Cabinet_ID.text = item.Cabinet_ID.toString()
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val IDItem :TextView = itemView.IDItem
        val name: TextView = itemView.name
        val VC: TextView = itemView.VC
        val Specification_Item :TextView = itemView.Specification_Item
        val About_Item :TextView = itemView.About_Item
        val User_ID :TextView = itemView.User_ID
        val Cabinet_ID :TextView = itemView.Cabinet_ID
            init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val IDItem = IDItem.text.toString()
            val name = name.text.toString()
            val VC = VC.text.toString()
            val Specification_Item = Specification_Item.text.toString()
            val About_Item = About_Item.text.toString()
            val User_ID = User_ID.text.toString()
            val Cabinet_ID = Cabinet_ID.text.toString()

            listener.onItemClick(IDItem.toInt(), name, VC, Specification_Item, About_Item, User_ID.toInt(), Cabinet_ID.toInt())
        }
    }

    interface OnItemClickListener{
        fun onItemClick(ID_Item: Int,
                        Name_Item: String,
                        Vendor_Code: String,
                        Specification_Item:String,
                        About_Item: String,
                        User_ID: Int,
                        Cabinet_ID: Int)
    }
}

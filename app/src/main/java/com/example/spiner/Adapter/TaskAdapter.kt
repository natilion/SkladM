package com.example.spiner.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spiner.Activity.SelectTask
import com.example.spiner.R
import com.example.spiner.models.Task
import kotlinx.android.synthetic.main.list_task.view.*

class TaskAdapter(private val tasks:List<Task>, private val listener: SelectTask):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.IDTask.text = task.ID_Task.toString()
        holder.Theme.text = task.Theme.toString()
        holder.Discription.text = task.Discription.toString()
        holder.Status.text = task.Status.toString()
        holder.User_ID!!.text = task.User_ID.toString()
        holder.Building_ID.text = task.Building_ID.toString()
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val IDTask : TextView = itemView.IDTask
        val Theme : TextView = itemView.Theme
        val Discription : TextView = itemView.Discription
        val Status : TextView = itemView.Status
        val User_ID : TextView? = itemView.UserID
        val Building_ID : TextView = itemView.Building_ID
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val IDTask = IDTask.text.toString()
            val Theme = Theme.text.toString()
            val Discription = Discription.text.toString()
            val Status = Status.text.toString()
            val User_ID = User_ID?.text.toString()
            val Building_ID = Building_ID.text.toString()

            listener.onItemClick(IDTask.toInt(), Theme, Discription, Status.toInt(), User_ID.toIntOrNull(), Building_ID.toInt())
        }
    }

    interface OnItemClickListener{
        fun onItemClick(ID_Task: Int,
                        Theme: String,
                        Discription: String,
                        Status: Int,
                        User_ID: Int?,
                        Building_ID: Int)
    }
}
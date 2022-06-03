package com.example.spiner.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiner.R
import com.example.spiner.TaskAdaptor
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_select_task.recycleView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_task)

        getSupportActionBar()?.setTitle(BuildingName)
        getData()
    }

    private fun getData(){
        api.fetchAllTasksByBuilding(BuildingId).enqueue(object : Callback<List<Task>>{
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {

            }
        })
    }

    private fun showData(tasks: List<Task>){
        recycleView.apply {
            layoutManager = LinearLayoutManager(this@SelectTask)
            adapter = TaskAdaptor(tasks, this@SelectTask)
        }
    }

    fun onItemClick(IDTask:Int, Theme:String, Discription:String, Status:Int, User_ID:Int?, Building_ID:Int) {
//        toast(this, "${IDTask} ${Theme} ${Discription} ${Status} ${User_ID} ${Building_ID} !")
        startActivity(Intent(this, OneTask::class.java)
            .putExtra("IDTask", IDTask)
            .putExtra("Theme", Theme)
            .putExtra("Discription", Discription)
            .putExtra("Status", Status)
            .putExtra("User_ID", User_ID)
            .putExtra("Building_ID", Building_ID))
    }
}
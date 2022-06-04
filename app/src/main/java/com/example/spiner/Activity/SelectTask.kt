package com.example.spiner.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiner.R
import com.example.spiner.Adapter.TaskAdapter
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_select_task.recycleView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectTask : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_task)

        getSupportActionBar()?.setTitle("Заявки: "+BuildingName)
        getData()

        SwipeRefreshLayout.setOnRefreshListener {
            getData()
            SwipeRefreshLayout.isRefreshing = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
         getData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getData(){
        if(net(this@SelectTask))
        api.fetchAllTasksByBuilding(BuildingId).enqueue(object : Callback<List<Task>>{
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) = showData(response.body()!!)
            override fun onFailure(call: Call<List<Task>>, t: Throwable) = toast(this@SelectTask, "Ошибка")
        })
        else toast(this@SelectTask, "Нет соединения с интернетом")
    }

    private fun showData(tasks: List<Task>){
        recycleView.apply {
            layoutManager = LinearLayoutManager(this@SelectTask)
            adapter = TaskAdapter(tasks, this@SelectTask)
        }
    }

    fun onItemClick(IDTask:Int, Theme:String, Discription:String, Status:Int, User_ID:Int?, Building_ID:Int) {
        startActivity(Intent(this, OneTask::class.java)
            .putExtra("IDTask", IDTask)
            .putExtra("Theme", Theme)
            .putExtra("Discription", Discription)
            .putExtra("Status", Status)
            .putExtra("User_ID", User_ID)
            .putExtra("Building_ID", Building_ID))
    }
}
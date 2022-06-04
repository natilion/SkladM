package com.example.spiner.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_new_task.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewTask : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        getSupportActionBar()?.setTitle("Создание запроса")

        Add.setOnClickListener {
            if(net(this@NewTask))
                if (Theme.text.isBlank() || Discription.text.isBlank()) toast(this@NewTask, "Заполните все поля")
                else add()
            else toast(this, "Нет соединения с интернетом")
        }
    }

    private fun add(){
        var newTask = Task()
        newTask.ID_Task = 0
        newTask.Theme = Theme.text.toString()
        newTask.Discription = Discription.text.toString()
        newTask.Status = 0
        newTask.User_ID //UserId
        newTask.Building_ID = BuildingId
        api.addTask(newTask).enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                toast(this@NewTask, "Запрос отправлен")
                finish()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                toast(this@NewTask, t.toString())
            }
        })
    }
}
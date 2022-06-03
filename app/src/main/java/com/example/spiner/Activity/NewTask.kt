package com.example.spiner.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_new_task.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewTask : AppCompatActivity() {

    public var idBui:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        getSupportActionBar()?.setTitle("Создание запроса")
        idBui = intent.getIntExtra("ID_Building", 0)

        Add.setOnClickListener { add() }
    }

    private fun add(){
        var newTask = Task()
        newTask.ID_Task = 0
        newTask.Theme = Theme.text.toString()
        newTask.Discription = Discription.text.toString()
        newTask.Status = 0
        newTask.User_ID //UserId
        newTask.Building_ID = idBui
        api.addTask(newTask).enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                toast(this@NewTask, "Запрос отправлен")
//                if (response.code() == 200) toast(this@NewTask, "Запрос отправлен")
//                else toast(this@NewTask, "Ошибка")
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                toast(this@NewTask, t.toString())
            }
        })
    }
}
package com.example.spiner.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_one_task.*
import kotlinx.android.synthetic.main.list_task.Discription
import kotlinx.android.synthetic.main.list_task.Theme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_task)

        val IDTask:Int = intent.getIntExtra("IDTask", 0)
        val theme: String? = intent.getStringExtra("Theme")
        val discription: String? = intent.getStringExtra("Discription")
        val status:Int = intent.getIntExtra("Status", 0)
        val user_ID:Int = intent.getIntExtra("User_ID", 0)
        val building_ID:Int = intent.getIntExtra("Building_ID", 0)

        Theme.setText(theme)
        Discription.setText(discription)

        var newTask = Task()
        newTask.ID_Task = IDTask
        newTask.Theme = theme
        newTask.Discription = discription
        newTask.Status = 1
        newTask.User_ID = UserId
        newTask.Building_ID = BuildingId

        Edit.setOnClickListener {
//            toast(this@OneTask, "${IDTask} ${theme} ${discription} ${status} ${user_ID} ${building_ID}")
            api.editTask(IDTask, newTask).enqueue(object : Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    toast(this@OneTask, "Yes")
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    toast(this@OneTask, "No")
                }
            })
        }
    }
}
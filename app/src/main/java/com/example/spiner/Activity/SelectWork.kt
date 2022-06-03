package com.example.spiner.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spiner.R
import com.example.spiner.models.BuildingName
import kotlinx.android.synthetic.main.activity_select_work.*

class SelectWork : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_work)

        getSupportActionBar()?.setTitle(BuildingName)

        bCabinet.setOnClickListener {
            startActivity(Intent(this, SelectCabinet::class.java))
        }

        bTask.setOnClickListener {
            startActivity(Intent(this, SelectTask::class.java))
        }
    }
}
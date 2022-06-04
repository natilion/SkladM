package com.example.spiner.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.spiner.Adapter.ListViewAdapter
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_select_cabinet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectCabinet : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_cabinet)

        getSupportActionBar()?.setTitle("Кабинеты: "+BuildingName)
        getData()
        list_Cabinet.setOnItemClickListener { parent, view, position, id ->
            val d = parent.adapter.getItemId(position)
            startActivity(Intent(this, MainActivity::class.java)
                .putExtra("ID_Cabinet", d))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getData(){
        var Adapter:List<Cabinet>
        if(net(this@SelectCabinet))
        api.fetchAllCabinetbyBuilding(BuildingId).enqueue(object : Callback<List<Cabinet>>{
            override fun onResponse(call: Call<List<Cabinet>>, response: Response<List<Cabinet>>) {
                Adapter = response.body()!!
                ls(Adapter)
            }
            override fun onFailure(call: Call<List<Cabinet>>, t: Throwable) {}
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        getData()
    }

    fun ls (list:List<Cabinet>){
        list_Cabinet.adapter = ListViewAdapter(this, list)
    }
}
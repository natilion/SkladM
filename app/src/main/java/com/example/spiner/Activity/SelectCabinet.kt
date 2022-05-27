package com.example.spiner.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spiner.ListViewAdapter
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_select_cabinet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectCabinet : AppCompatActivity() {
//    val retrofit = Retrofit.Builder().baseUrl(link)
//        .addConverterFactory(GsonConverterFactory.create()).build()
//    val api = retrofit.create(ApiInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_cabinet)

        val idBui = intent.getIntExtra("ID_Building", 0)
        val NameBuilding = intent.getStringExtra("Name_Building")
        getSupportActionBar()?.setTitle(NameBuilding)
        var Adapter:List<Cabinet>
        api.fetchAllCabinetbyBuilding(idBui).enqueue(object : Callback<List<Cabinet>>{
            override fun onResponse(call: Call<List<Cabinet>>, response: Response<List<Cabinet>>) {
                Adapter = response.body()!!
                ls(Adapter)
            }
            override fun onFailure(call: Call<List<Cabinet>>, t: Throwable) {}
        })

        list_Cabinet.setOnItemClickListener { parent, view, position, id ->
            val d = parent.adapter.getItemId(position)
            startActivity(Intent(this, MainActivity::class.java)
                .putExtra("ID_Cabinet", d))
        }
    }
    fun ls (list:List<Cabinet>){
        list_Cabinet.adapter = ListViewAdapter(this, list)
    }
}
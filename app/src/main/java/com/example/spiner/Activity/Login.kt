package com.example.spiner.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import com.example.spiner.ApiInterface
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getSupportActionBar()?.setTitle("Авторизация")

        loudData()
        button.setOnClickListener {
            val login:String = Login.text.toString()
            val password:String = Password.text.toString()

            api.loginUser(login, password).enqueue(object :Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if(response.code() == 200) {
                        Toast.makeText(this@Login, "${spinerBuilding.selectedItem}", Toast.LENGTH_SHORT).show()
                        if (response.body()!!.isNotEmpty()) new(
                            spinerBuilding.selectedItemPosition + 1,
                            spinerBuilding.selectedItem.toString(),
                            response.body()!!.get(0).ID_User
                        )
                    }
                }
                override fun onFailure(call: Call<List<User>>, t: Throwable){ }
            })
        }
        task.setOnClickListener {
            startActivity(Intent(this, NewTask::class.java)
                .putExtra("ID_Building", spinerBuilding.selectedItemPosition+1))
        }
    }
    override fun onResume() {
        super.onResume()
        loudData()
    }

    private fun loudData(){
        api.fetchAllBuildings().enqueue(object : Callback<List<Building>> {
            override fun onResponse(call: Call<List<Building>>, response: Response<List<Building>>) =
                setDataToSpinerBuilding(response.body()!!.map { it.Address })
            override fun onFailure(call: Call<List<Building>>, t: Throwable) {}
        })
    }

    private fun setDataToSpinerBuilding(data:List<String>){
        val arrayAd = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, data)
        spinerBuilding.adapter = arrayAd
    }

    private fun new(idBuilding:Int, NameBuilding:String, idUser:Int) {
        startActivity(
            Intent(this, SelectCabinet::class.java)
                .putExtra("Name_Building", NameBuilding)
                .putExtra("ID_Building", idBuilding.toInt())
        )
        UserId = idUser
    }
}
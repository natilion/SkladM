package com.example.spiner.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getSupportActionBar()?.setTitle("Авторизация")
        if (net(this))
            loudData()
        else toast(this, "Нет соединения с интернетом")
        button.setOnClickListener {
            if(net(this))
                if (Login.text.isBlank() || Password.text.isBlank())
                    toast(this@Login, "Заполните все поля")
                else {
                    val login: String = Login.text.toString()
                    val password: String = Password.text.toString()

                    api.loginUser(login, password).enqueue(object : Callback<List<User>> {
                        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                            if (response.code() == 200) {
                                if (response.body()!!.isNotEmpty()) new(
                                    spinerBuilding.selectedItemPosition + 1,
                                    spinerBuilding.selectedItem.toString(),
                                    response.body()!!.get(0).ID_User
                                )
                            }
                        }
                        override fun onFailure(call: Call<List<User>>, t: Throwable) = toast(this@Login, "Нет соединения")
                    })
                }
            else toast(this, "Нет соединения с интернетом")
        }
        task.setOnClickListener {
            if(net(this))
                startActivity(Intent(this, NewTask::class.java)
                    .putExtra("ID_Building", spinerBuilding.selectedItemPosition+1))
            else toast(this, "Нет соединения с интернетом")
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        if(net(this)) loudData()
        else toast(this, "Нет соединения с интернетом")
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
            Intent(this, SelectWork::class.java)
        )
        UserId = idUser
        BuildingId = idBuilding
        BuildingName = NameBuilding
        Login.text.clear()
        Password.text.clear()
    }
}
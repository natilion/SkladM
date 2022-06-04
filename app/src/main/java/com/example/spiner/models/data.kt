package com.example.spiner.models

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.spiner.Interface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public val link : String = "https://e27c-91-215-188-116.eu.ngrok.io"
public var UserId : Int = 0
public var BuildingId : Int = 0
public var BuildingName : String = ""

val retrofit = Retrofit.Builder().baseUrl(link)
    .addConverterFactory(GsonConverterFactory.create()).build()
val api = retrofit.create(ApiInterface::class.java)
//main
fun toast(con:Context, msg:String) = Toast.makeText(con, "${msg}", Toast.LENGTH_LONG).show()

@RequiresApi(Build.VERSION_CODES.M)
fun net(con:Context): Boolean {
    val cm : ConnectivityManager = con.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetwork != null
}
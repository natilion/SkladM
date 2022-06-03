package com.example.spiner.models

import android.content.Context
import android.widget.Toast
import com.example.spiner.ApiInterface
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
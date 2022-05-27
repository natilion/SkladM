package com.example.spiner

import androidx.core.location.LocationRequestCompat
import com.example.spiner.models.*
import okhttp3.RequestBody
import org.json.JSONObject
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("/api/Items")
    fun fetchAllItems(): Call<List<Item>>

    @GET("/api/Item/{id}")
    fun fetchOneItem(@Path("id") id:Int): Call<List<Item>>

    @GET("/api/searchItembyCabinet/{id}")
    fun fetchAllItemsByCabinet(@Path("id") id:Int): Call<List<Item>>

    @GET("/api/itemsearch/{request}")
    fun fetchAllItemsByRequest(@Path("request") request:String): Call<List<Item>>

    @POST("/api/Item")
    fun addItem(@Body newItem: Item): Call<Unit>

    @PUT("/api/Item/{id}")
    fun editItem(@Path("id") id:Int, @Body newItem: Item): Call<Unit>

    @DELETE("/api/Item/{id}")
    fun deleteOneItem(@Path("id") id:Int): Call<Unit>

    @GET("/api/Buildings")
    fun fetchAllBuildings(): Call<List<Building>>

    @GET("/api/Cabinets")
    fun fetchAllCabinets(): Call<List<Cabinet>>

    @GET("/api/Cabinet/{id}")
    fun fetchOneCabinet(@Path("id") id:Int): Call<List<Cabinet>>

    @GET("/api/searchCabinetbyBuilding/{id}")
    fun fetchAllCabinetsByBiulding(@Path("id") id:Int): Call<List<Cabinet>>

    @GET("/api/searchCabinetbyBuilding/{id}")
    fun fetchAllCabinetbyBuilding(@Path("id") id:Int): Call<List<Cabinet>>

    @GET("/api/UserLog/{Login}/{Password}")
    fun loginUser(@Path("Login") Login:String, @Path("Password") Password:String): Call<List<User>>

    //TASKS

    @POST("/api/Task")
    fun addTask(@Body newTask: Task): Call<Unit>
}
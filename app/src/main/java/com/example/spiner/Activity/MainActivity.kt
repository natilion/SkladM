package com.example.spiner.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiner.ItemAdaptor
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ItemAdaptor.OnItemClickListener {

    var idCab : Long = 0
    var nameCab : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idCab = intent.getLongExtra("ID_Cabinet", 0)
        api.fetchOneCabinet(idCab.toInt()).enqueue(object : Callback<List<Cabinet>>{
            override fun onResponse(call: Call<List<Cabinet>>, response: Response<List<Cabinet>>) {
                getSupportActionBar()?.setTitle(response.body()!!.get(0).Name_Cabinet)
            }
            override fun onFailure(call: Call<List<Cabinet>>, t: Throwable){}

        })
        getData(idCab)

        AddBtn.setOnClickListener {
            startActivity(Intent(this, AddEditItem::class.java).putExtra("idCabinet", idCab))
        }

        SwipeRefreshLayout.setOnRefreshListener {
            getData(idCab)
            SwipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        getData(idCab)
    }

    private fun getData(id:Long){
        api.fetchAllItemsByCabinet(id.toInt()).enqueue(object : Callback<List<Item>>{
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) = showData(response.body()!!)
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {}
        })
    }

    private fun showData(items: List<Item>){
        recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ItemAdaptor(items, this@MainActivity)
        }
    }

    override fun onItemClick(ID_Item: Int, Name_Item: String, Vendor_Code: String,
                             Specification_Item:String, About_Item: String, User_ID: Int, Cabinet_ID: Int) {
        startActivity(Intent(this, AddEditItem::class.java)
            .putExtra("ID_Item", ID_Item)
            .putExtra("Name_Item", Name_Item)
            .putExtra("Vendor_Code", Vendor_Code)
            .putExtra("Specification_Item", Specification_Item)
            .putExtra("About_Item", About_Item)
            .putExtra("User_ID", User_ID)
            .putExtra("Cabinet_ID", Cabinet_ID))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val search = menu?.findItem(R.id.nav_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Поиск..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(text: String?): Boolean {
                toast(this@MainActivity, "text")
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                if (text == "") getData(idCab)
                else{
                    api.fetchAllItemsByRequest(text).enqueue(object : Callback<List<Item>>{
                        override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) = showData(response.body()!!)
                        override fun onFailure(call: Call<List<Item>>, t: Throwable) {}
                    })
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
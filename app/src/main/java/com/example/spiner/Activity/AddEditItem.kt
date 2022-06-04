package com.example.spiner.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.spiner.R
import com.example.spiner.models.*
import kotlinx.android.synthetic.main.activity_add_edit_item.*
import kotlinx.android.synthetic.main.activity_add_edit_item.About_Item
import kotlinx.android.synthetic.main.activity_add_edit_item.Specification_Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEditItem : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_item)
        val idCab = intent.getLongExtra("idCabinet", 1)

        val idItem = intent.getIntExtra("ID_Item", 0)
        val idUser = UserId
        val idCabinet = intent.getIntExtra("Cabinet_ID", 2)

        Name.setText(intent.getStringExtra("Name_Item"))
        Vendor_Code.setText(intent.getStringExtra("Vendor_Code"))
        Specification_Item.setText(intent.getStringExtra("Specification_Item"))
        About_Item.setText(intent.getStringExtra("About_Item"))

        if (idItem == 0) {
            Add.setText("Добавить")
            getSupportActionBar()?.setTitle("Добавление")
            Del.isEnabled = false
        } else {
            Add.setText("Редактировать")
            getSupportActionBar()?.setTitle("Редактирование")
        }

        Add.setOnClickListener {
            if(net(this@AddEditItem)) {
                var newItem = Item()
                newItem.ID_Item = idItem
                newItem.Name_Item = Name.text.toString()
                newItem.Vendor_Code = Vendor_Code.text.toString()
                newItem.Specification_Item = Specification_Item.text.toString()
                newItem.About_Item = About_Item.text.toString()
                newItem.User_ID = UserId
                if (Name.text.isBlank() || Vendor_Code.text.isBlank() || Specification_Item.text.isBlank() || About_Item.text.isBlank())
                    toast(this@AddEditItem, "Заполните все поля")
                else {
                    if (idItem == 0) {
                        newItem.Cabinet_ID = idCab.toInt()
                        api.addItem(newItem).enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                Toast.makeText(
                                    this@AddEditItem,
                                    "Предмет добавлен",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                finish()
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Toast.makeText(this@AddEditItem, "${t}", Toast.LENGTH_SHORT).show()
                                About_Item.setText(t.toString())
                            }

                        })
                    } else {
                        newItem.Cabinet_ID = idCabinet
                        api.editItem(idItem, newItem).enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                Toast.makeText(
                                    this@AddEditItem,
                                    "Предмет отредактирован",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Toast.makeText(this@AddEditItem, "${t}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                }
            }
        }

        Del.setOnClickListener {
            if(net(this@AddEditItem))
                api.deleteOneItem(idItem).enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        Toast.makeText(this@AddEditItem, "Предмет удалён", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(this@AddEditItem, "Ошибка = ${t}", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })
        }
    }
}
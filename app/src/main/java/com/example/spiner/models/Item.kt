package com.example.spiner.models

import com.google.gson.annotations.SerializedName

data class Item(
//    @SerializedName("ID_Item")
    var ID_Item: Int = 0,
//    @SerializedName("Name_Item")
    var Name_Item: String? = null,
//    @SerializedName("Vendor_Code")
    var Vendor_Code: String? = null,
//    @SerializedName("Specification_Item")
    var Specification_Item: String? = null,
//    @SerializedName("About_Item")
    var About_Item: String? = null,
//    @SerializedName("User_ID")
    var User_ID: Int = 0,
//    @SerializedName("Cabinet_ID")
    var Cabinet_ID: Int = 0
)
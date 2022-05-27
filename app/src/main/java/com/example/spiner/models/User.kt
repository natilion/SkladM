package com.example.spiner.models

data class User(
    var ID_User: Int = 0,
    var Name_User: String? = null,
    var Last_Name_User: String? = null,
    var Email: String? = null,
    var Login: String? = null,
    var Password: String? = null,
    var Role: Int = 0
)
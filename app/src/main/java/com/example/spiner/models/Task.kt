package com.example.spiner.models

data class Task(
    var ID_Task: Int = 0,
    var Theme: String? = null,
    var Discription: String? = null,
    var Status: Int = 0,
    var User_ID: Int? = null,
    var Building_ID: Int = 0
)
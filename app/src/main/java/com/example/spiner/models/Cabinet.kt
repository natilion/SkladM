package com.example.spiner.models

data class Cabinet(
    val ID_Cabinet: Int,
    val Name_Cabinet: String,
    val Description: String,
    val Building_ID: Int
) {
    override fun toString(): String {
        return super.toString()
    }
}
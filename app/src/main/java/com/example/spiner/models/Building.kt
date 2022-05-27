package com.example.spiner.models

data class Building(
    val ID_Building: Int,
    val Address: String)
{
    override fun toString(): String {
        return Address
    }
}

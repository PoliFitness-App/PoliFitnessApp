package com.uca.polifitnessapp.network.dto.news

data class NewsResponse(
    val tittle: String,
    val description: String,
    val image: String,
    val category: String,
    val user: String, //tengo dudas del tipo de dato
    val hidden: Boolean
)

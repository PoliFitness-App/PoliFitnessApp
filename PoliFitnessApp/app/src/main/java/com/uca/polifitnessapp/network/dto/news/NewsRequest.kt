package com.uca.polifitnessapp.network.dto.news

data class NewsRequest(
    val tittle: String,
    val description: String,
    val image: String,
    val category: String,
    val user: Int //tengo dudas del tipo de dato
)

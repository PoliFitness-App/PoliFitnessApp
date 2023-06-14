package com.uca.polifitnessapp.network.dto.update

data class UpdateRequest(
    val weight: Float,
    val height: Float,
    val waistP: String,
    val hipP: String,
    val _id: String
)
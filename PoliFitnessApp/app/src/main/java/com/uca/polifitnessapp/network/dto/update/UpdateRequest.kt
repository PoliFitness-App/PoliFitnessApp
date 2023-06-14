package com.uca.polifitnessapp.network.dto.update

data class UpdateRequest(
    val weight: Float,
    val height: Float,
    val waistP: Float,
    val hipP: Float,
    val _id: String
)
package com.uca.polifitnessapp.network.dto.update

data class UpdateRequest(
    val weight: Float,
    val height: Float,
    val waistP: Float,
    val hipP: Float,
    val _id: String,
    val approach: String,
    val icc: Float,
    val imc: Float,
)
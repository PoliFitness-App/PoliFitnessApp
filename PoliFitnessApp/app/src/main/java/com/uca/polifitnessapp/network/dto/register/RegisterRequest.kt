package com.uca.polifitnessapp.network.dto.register

data class RegisterRequest(
    // RegisterRequest
    val username: String,
    val lastname: String,
    val email: String,
    val password: String,
    val imc : Float,
    val icc : Float,
    val gender : Boolean,
    val birthday: String,
    val weight: Float,
    val height: Float,
    val waistP: Float,
    val hipP: Float,
    val approach: String
)
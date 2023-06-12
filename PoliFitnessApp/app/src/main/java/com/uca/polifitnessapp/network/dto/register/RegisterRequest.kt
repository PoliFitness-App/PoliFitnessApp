package com.uca.polifitnessapp.network.dto.register

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
)
package com.uca.polifitnessapp.network.dto.login

data class LoginRequest(
    val identifier: String,
    val password: String,
)
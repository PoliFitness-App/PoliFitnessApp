package com.uca.polifitnessapp.network.service

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("api/auth/register")
    suspend fun register(@Body credentials: RegisterRequest): RegisterResponse
}
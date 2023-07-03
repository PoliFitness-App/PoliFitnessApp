package com.uca.polifitnessapp.network.service

import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.network.dto.login.LoginRequest
import com.uca.polifitnessapp.network.dto.login.LoginResponse
import com.uca.polifitnessapp.network.dto.register.RegisterRequest
import com.uca.polifitnessapp.network.dto.register.RegisterResponse
import com.uca.polifitnessapp.network.dto.update.UpdateRequest
import com.uca.polifitnessapp.network.dto.update.UpdateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/signin")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("api/auth/signup")
    suspend fun register(@Body credentials: RegisterRequest): RegisterResponse

    @GET("api/auth/whoami")
    suspend fun whoami(): UserModel

    @POST("api/auth/update")
    suspend fun update(@Body credentials: UpdateRequest): UpdateResponse
}
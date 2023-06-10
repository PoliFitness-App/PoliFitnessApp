package com.uca.polifitnessapp.network.retrofit

import com.uca.polifitnessapp.network.service.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.deportivouca.tech/"

object RetrofitInstance {

    private var token = ""

    fun setToken(token: String) {
        this.token = token
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLoginService(): AuthService = retrofit.create(AuthService::class.java)

}
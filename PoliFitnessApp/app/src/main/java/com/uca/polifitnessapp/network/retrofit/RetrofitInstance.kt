package com.uca.polifitnessapp.network.retrofit

import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.network.dto.whoami.WhoamiReponse
import com.uca.polifitnessapp.network.interceptor.LoginInterceptor
import com.uca.polifitnessapp.network.service.AuthService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.deportivouca.tech/"

object RetrofitInstance {

    private var token = ""

    fun setToken(token: String) {
        this.token = token
    }
    fun getToken(): String = token

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                    )
                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getLoginService(): AuthService = retrofit.create(AuthService::class.java)
}
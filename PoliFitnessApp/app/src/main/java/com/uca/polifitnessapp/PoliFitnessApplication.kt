package com.uca.polifitnessapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.network.retrofit.RetrofitInstance
import com.uca.polifitnessapp.repositories.CredentialsRepository
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.repositories.RoutineRepository
import com.uca.polifitnessapp.repositories.UserRepository

class PoliFitnessApplication: Application(){
    //implementando la base de datos
    private val database: PoliFitnessDatabase by lazy {
        PoliFitnessDatabase.newInstance(this)
    }


    //implementacion de los repositorios de la aplicacion
    val noticeRepository: NoticeRepository by lazy {
        NoticeRepository(database, retrofitInstance.getPoliFitnessAPINews())
    }

    val routineRepository: RoutineRepository by lazy {
        RoutineRepository(database.routineDao())
    }

    val userRepository: UserRepository by lazy {
        UserRepository(database.userDao())
    }


    // INSTANCIA DE RETROFIT
    val retrofitInstance: RetrofitInstance by lazy {
        RetrofitInstance
    }

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("Retrofit", Context.MODE_PRIVATE)
    }

    private fun getAPIService() = with(RetrofitInstance){
        setToken(getToken())
        getLoginService()
    }

    fun getToken(): String = prefs.getString(USER_TOKEN, "")!!

    val credentialRepository: CredentialsRepository by lazy{
        CredentialsRepository(getAPIService())
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }

}
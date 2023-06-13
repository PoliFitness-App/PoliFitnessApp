package com.uca.polifitnessapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.models.UserModel
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
        NoticeRepository(database.noticeDao())
    }

    val routineRepository: RoutineRepository by lazy {
        RoutineRepository(database.routineDao())
    }

    val userRepository: UserRepository by lazy {
        UserRepository(database.userDao())
    }

    // Retrofit aplication
    // Retrofit instance
    private val retrofitInstance by lazy {
        RetrofitInstance
    }

    // Shared preferences instance
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("application", Context.MODE_PRIVATE)
    }

    // User token -- get token
    fun getToken(): String = prefs.getString(USER_TOKEN, "")!!

    // credentialsRepository
    val credentialRepository: CredentialsRepository by lazy{
        CredentialsRepository(retrofitInstance.getLoginService(), database.userDao())
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
        retrofitInstance.setToken(getToken())
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }

}
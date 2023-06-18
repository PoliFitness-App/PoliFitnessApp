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
        NoticeRepository(database, retrofitInstance.getPoliFitnessAPINews())
    }

    val routineRepository: RoutineRepository by lazy {
        RoutineRepository(database.routineDao())
    }

    val userRepository: UserRepository by lazy {
        UserRepository(database.userDao())
    }

    // ---
    // Retrofit instance
    // ---

    private val retrofitInstance by lazy {
        RetrofitInstance
    }

    // ---
    // Shared preferences
    // ---

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("application", Context.MODE_PRIVATE)
    }

    // ---
    // User
    // ---
    fun getToken(): String = prefs.getString(USER_TOKEN, "")!!
    fun getUserState(): Boolean = prefs.getBoolean(USER_STATE, false)

    // ---
    // Repositories
    // ---

    // CredentialsRepository
    val credentialRepository: CredentialsRepository by lazy{
        CredentialsRepository(retrofitInstance.getLoginService(), database.userDao())
    }

    // ---
    // Functions
    // ---

    // Fun save auth token
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
        retrofitInstance.setToken(getToken())
    }
    // Fun save user state
    fun saveUserState(state: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(USER_STATE, state)
        editor.apply()
    }

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_STATE = "user_state"
    }
}
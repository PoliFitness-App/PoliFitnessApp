package com.uca.polifitnessapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.TypeConverters
import com.uca.polifitnessapp.data.PoliFitnessDatabase
import com.uca.polifitnessapp.network.retrofit.RetrofitInstance
import com.uca.polifitnessapp.repositories.CredentialsRepository
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.repositories.RoutineRepository
import com.uca.polifitnessapp.ui.auth.login.state.UserState

class PoliFitnessApplication: Application(){

    // ---
    // Database instance
    // ---
    private val database: PoliFitnessDatabase by lazy {
        PoliFitnessDatabase.newInstance(this)
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
    fun getUserState(): String = prefs.getString(USER_STATE, UserState.NEW_USER)!!

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
    fun saveUserState(state: String) {
        val editor = prefs.edit()
        editor.putString(USER_STATE, state)
        editor.apply()
    }

    // ---
    // Repositories
    // ---

    // Credentials repository
    val credentialRepository: CredentialsRepository by lazy{
        CredentialsRepository(retrofitInstance.getLoginService(), database.userDao())
    }

    // News repository
    val noticeRepository: NoticeRepository by lazy {
        NoticeRepository(database, retrofitInstance.getPoliFitnessAPINews())
    }

    // Routines repository
    val routineRepository: RoutineRepository by lazy {
        RoutineRepository(database, retrofitInstance.getPoliFitnessAPIRoutines())
    }

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_STATE = "user_state"
    }
}
package com.uca.polifitnessapp.ui.user.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.repositories.CredentialsRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // ---
    // User View Model
    //

    // User instance
    var user by mutableStateOf(
        UserModel(
            "",
            "",
            "",
            0F,
            0F,
            0F,
            0F,
            ""
        )
    )
        private set


    // Function to get the user
    fun getUser() {
        viewModelScope.launch {
            val response = repository.whoami()
            if (response is ApiResponse.Success) {
                user = response.data
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            val response = repository.getUserDAO()
            if (response != null) {
                user = response
                println(user)
            }
        }
    }

    fun fetchUserById(id: String) {
        viewModelScope.launch {
            try {
                // Call repository function
                val userA = repository.getUserById(id)
                user = userA!!
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    // Companion object to initialize the view model(UserViewModel)
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                UserViewModel(app.credentialRepository)
            }
        }
    }

}
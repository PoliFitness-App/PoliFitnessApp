package com.uca.polifitnessapp.ui.user.viewmodel

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

    // UserViewModel
    // User instance
    private val _user = MutableLiveData<UserModel>()
    val user: MutableLiveData<UserModel>
        get() = _user

    // password
    private val _password = MutableLiveData("")
    val password: MutableLiveData<String>
        get() = _password

    // Function to get the user
    fun getUser() {
        viewModelScope.launch {
            val response = repository.whoami()
            if (response is ApiResponse.Success) {
                user.value = response.data
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
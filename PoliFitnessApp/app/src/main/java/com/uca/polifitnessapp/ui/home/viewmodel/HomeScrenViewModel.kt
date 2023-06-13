package com.uca.polifitnessapp.ui.home.viewmodel

import android.widget.Toast
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
import com.uca.polifitnessapp.ui.login.ui.LoginUiStatus
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class HomeScrenViewModel(private val repository: CredentialsRepository) : ViewModel() {

    // Factory
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                HomeScrenViewModel(app.credentialRepository)
            }
        }
    }
}
package com.uca.polifitnessapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.repositories.CredentialsRepository

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
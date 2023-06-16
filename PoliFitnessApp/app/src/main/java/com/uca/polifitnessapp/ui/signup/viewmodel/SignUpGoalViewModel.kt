package com.uca.polifitnessapp.ui.signup.viewmodel

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
import com.uca.polifitnessapp.ui.signup.validation.SignUpUiStatus
import kotlinx.coroutines.launch

class SignUpGoalViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // ---
    // Variables
    // ---

    // Approach
    var approach = MutableLiveData("")

    // Is enable?
    private val _isEnabled = MutableLiveData(false)
    val isEnabled: MutableLiveData<Boolean>
        get() = _isEnabled

    // ---
    // Status
    // --

    private val _status = MutableLiveData<SignUpUiStatus>(SignUpUiStatus.Resume)
    val status: MutableLiveData<SignUpUiStatus>
        get() = _status

    // ---
    // Functions
    // ---

    // Signup function -> Calls the repository to signup, and updates the status
    private fun signUp(user: UserModel, password: String) {
        viewModelScope.launch {
            _status.postValue(
                // Calls the repository to signup
                when (val response =
                    repository.register(
                        user,
                        password
                    )
                ) {
                    // If there is an error, it returns an error
                    is ApiResponse.Error -> SignUpUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> SignUpUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> SignUpUiStatus.Success
                }
            )
        }
    }

    // On sign up function -> Calls the signup function
    fun onSignUp(user: UserModel, password: String){

        // If the password is empty, it returns an error
        if (password.isEmpty()) {
            _status.value = SignUpUiStatus.ErrorWithMessage("Password is empty")
            return
        }

        // If the password is not empty, it calls the signup function
        signUp(user, password)
    }

    // On approach function -> Updates the approach variable
    fun onApproachChange(approachU: String) {

        // If the approach is empty, it returns an error
        if (approachU.isEmpty()) {
            _status.value = SignUpUiStatus.ErrorWithMessage("Approach is empty")
            return
        }

        // If the approach is not empty, it updates the approach variable
        approach.value = approachU
        // It also updates the is enabled variable
        _isEnabled.value = true
    }

    // ---
    // Auxiliary functions
    // ---

    fun clearData() {
        approach.value = ""
    }

    fun clearStatus() {
        _status.value = SignUpUiStatus.Resume
    }

    // ---
    // Companion object
    // ---

    // Factory of the view model
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                SignUpGoalViewModel(app.credentialRepository)
            }
        }
    }
}


package com.uca.polifitnessapp.ui.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.repositories.CredentialsRepository
import com.uca.polifitnessapp.ui.login.state.LoginUiStatus
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // status for view model
    // email
    var email = MutableLiveData("")

    // password
    var password = MutableLiveData("")

    var token = MutableLiveData("")

    // login enable
    private val _isLoginEnable = MutableLiveData(false)
    val isLoginEnable: MutableLiveData<Boolean>
        get() = _isLoginEnable

    // is valid email
    private val _isValidEmail = MutableLiveData(false)
    val isValidEmail: MutableLiveData<Boolean>
        get() = _isValidEmail

    // is valid password
    private val _isValidPassword = MutableLiveData(false)
    val isValidPassword: MutableLiveData<Boolean>
        get() = _isValidPassword

    // status
    private val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)
    val status: MutableLiveData<LoginUiStatus>
        get() = _status

    // is loading?
    private val _isLoading = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean>
        get() = _isLoading

    // LogIn
    private fun login(email: String, password: String) {

        viewModelScope.launch {
            _status.postValue(
                when (val response = repository.login(email, password)) {
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }
            )
        }
        _isLoading.value = false
    }

    // On Login
    fun onLogin() {

        _isLoading.value = true

        if (!validateData()) {
            _status.value = LoginUiStatus.ErrorWithMessage("Invalid credentials email or password")
            return
        }

        _isValidEmail.value = !isValidEmail(email.value!!)

        _isValidPassword.value = !isValidPassword(password.value!!)

        login(email.value!!, password.value!!)
    }

    // On Login Changed
    fun onLoginChanged(emailU: String, passwordU: String) {
        // verify if the email fortmat is correct
        _isValidEmail.value = !isValidEmail(email.value!!)

        //then
        email.value = emailU.trimEnd()
        password.value = passwordU.trim()

        _isLoginEnable.value = isValidEmail(emailU) && isValidPassword(passwordU)
    }

    // Validate data
    private fun validateData(): Boolean {
        when {
            email.value.isNullOrEmpty() -> return false
            password.value.isNullOrEmpty() -> return false
        }
        return true
    }

    // Functions to validate if the inputs are wrong
    // is valid password
    private fun isValidPassword(password: String): Boolean =
        password.length >= 8

    // is valid email
    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun clearData() {
        email.value = ""
        password.value = ""
    }

    fun clearStatus() {
        _status.value = LoginUiStatus.Resume
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                LoginViewModel(app.credentialRepository)
            }
        }
    }
}
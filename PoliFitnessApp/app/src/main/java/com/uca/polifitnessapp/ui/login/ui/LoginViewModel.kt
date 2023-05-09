package com.uca.polifitnessapp.ui.login.ui

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {

    // EMAIL

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    // PASSWORD

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    // LOGIN ENABLE

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    // IS LOADING

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // ON WRONG INPUT

    private val _isWrongInput = MutableLiveData<Boolean>()
    val isWrongInput: LiveData<Boolean> = _isWrongInput

    // ON LOGIN CHANGED

    fun onLoginChanged(email: String, password: String) {
        _email.value = email.trimEnd()
        _password.value = password.trim()
        _loginEnable.value =
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (isValidEmail(email) && isValidPassword(password)) {
                    _isWrongInput.value = false
                }
                true
            } else {
                _isWrongInput.value = true
                false
            }
    }

    // IS VALID PASSWORD

    private fun isValidPassword(password: String): Boolean = password.length >= 8

    // IS VALID EMAIL

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)

        _isLoading.value = false
    }

}
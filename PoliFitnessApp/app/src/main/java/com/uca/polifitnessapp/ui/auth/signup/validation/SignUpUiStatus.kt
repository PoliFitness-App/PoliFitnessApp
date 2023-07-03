package com.uca.polifitnessapp.ui.auth.signup.validation

import java.lang.Exception

sealed class SignUpUiStatus{
    object Resume : SignUpUiStatus()
    data class ErrorWithMessage(val message: String) : SignUpUiStatus()
    data class Error(val exception: Exception) : SignUpUiStatus()
    object Success : SignUpUiStatus()
}

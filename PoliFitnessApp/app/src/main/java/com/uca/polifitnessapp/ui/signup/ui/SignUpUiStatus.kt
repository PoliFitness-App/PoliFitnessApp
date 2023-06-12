package com.uca.polifitnessapp.ui.signup.ui

import java.lang.Exception

sealed class SignUpUiStatus{

    object Resume : SignUpUiStatus()
    data class ErrorWithMessage(val message: String) : SignUpUiStatus()
    data class Error(val exception: Exception) : SignUpUiStatus()
    object Success : SignUpUiStatus()
}

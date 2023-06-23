package com.uca.polifitnessapp.ui.login.state

import java.lang.Exception

sealed class LoginUiStatus {
    // Login Status
    // Resume status
    object Resume : LoginUiStatus()

    /// Error with exception status
    class Error(val exception: Exception) : LoginUiStatus()

    // Error with message status
    data class ErrorWithMessage(val message: String) : LoginUiStatus()
    // Error with success status
    data class Success(val token: String) : LoginUiStatus()
}
package com.uca.polifitnessapp.ui.main.home.state

import java.lang.Exception

sealed class HomeUiStatus {
    // Home UI Status
    // Resume status
    object Resume : HomeUiStatus()
    // Loading status
    object Loading : HomeUiStatus()
    /// Error with exception status
    class Error(val exception: Exception) : HomeUiStatus()

    // Error with message status
    data class ErrorWithMessage(val message: String) : HomeUiStatus()
    // Error with success status
    data class Success(val message: String) : HomeUiStatus()
}
package com.uca.polifitnessapp.ui.user.status

import java.lang.Exception

sealed class EditProfileUiStatus {
    // EditProfile Status
    // Resume status
    object Resume : EditProfileUiStatus()

    /// Error with exception status
    class Error(val exception: Exception) : EditProfileUiStatus()

    // Error with message status
    data class ErrorWithMessage(val message: String) : EditProfileUiStatus()

    // Error with success status
    data class Success(val message: String) : EditProfileUiStatus()
}
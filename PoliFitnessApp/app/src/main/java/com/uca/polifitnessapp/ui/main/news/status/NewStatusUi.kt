package com.uca.polifitnessapp.ui.main.news.status

import java.lang.Exception

sealed class NewStatusUi {
    // NewStatusUi Status
    // Resume status
    object Resume : NewStatusUi()

    /// Error with exception status
    class Error(val exception: Exception) : NewStatusUi()

    // Error with message status
    data class ErrorWithMessage(val message: String) : NewStatusUi()

    // Error with success status
    data class Success(val message: String) : NewStatusUi()
}
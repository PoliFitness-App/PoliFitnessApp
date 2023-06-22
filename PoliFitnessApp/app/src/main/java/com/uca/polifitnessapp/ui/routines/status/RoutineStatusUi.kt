package com.uca.polifitnessapp.ui.routines.status

import java.lang.Exception

sealed class RoutineStatusUi {
    // NewStatusUi Status
    // Resume status
    object Resume : RoutineStatusUi()

    /// Error with exception status
    class Error(val exception: Exception) : RoutineStatusUi()

    // Error with message status
    data class ErrorWithMessage(val message: String) : RoutineStatusUi()

    // Error with success status
    data class Success(val message: String) : RoutineStatusUi()
}
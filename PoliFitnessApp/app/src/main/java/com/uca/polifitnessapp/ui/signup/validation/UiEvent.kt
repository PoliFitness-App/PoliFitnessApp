package com.uca.polifitnessapp.ui.signup.validation

sealed class UiEvent {

    data class NameChanged( val name: String ): UiEvent()
    data class Lastnamehanged( val lastname: String ): UiEvent()
    data class EmailChanged( val email: String ): UiEvent()
    data class PasswordChanged( val password: String ): UiEvent()

    object SignUpButtonClicked : UiEvent()

}
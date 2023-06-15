package com.uca.polifitnessapp.ui.signup.validation

data class SignUpUiState(
    var name: String = "",
    var lastname: String = "",
    var email: String = "",
    var password: String = "",

    var nameError: Boolean = false,
    var lastnameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false
)

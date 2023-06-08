package com.uca.polifitnessapp.ui.signup.ui.validation

object Validator {

    fun validateName(name: String): ValidationResult{

        return ValidationResult(
            (!name.isNullOrEmpty() && name.length >= 6)
        )

    }

    fun validateLastName(lastname: String): ValidationResult{

        return ValidationResult(
            (!lastname.isNullOrEmpty() && lastname.length >= 6)
        )
    }

    fun validateEmail(email: String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )

    }

    fun validatePassword(password: String): ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 8)
        )
    }
}

data class ValidationResult(
    val status:Boolean =  false
)
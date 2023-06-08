package com.uca.polifitnessapp.ui.signup.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.uca.polifitnessapp.ui.signup.ui.validation.SignUpUiState
import com.uca.polifitnessapp.ui.signup.ui.validation.UiEvent
import com.uca.polifitnessapp.ui.signup.ui.validation.Validator

class SignUpViewModel: ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName

    var signUpUiState = mutableStateOf(SignUpUiState())

    fun onEvent( event: UiEvent){
        when( event){
            is UiEvent.NameChanged ->{
                signUpUiState.value = signUpUiState.value.copy(
                name = event.name)

                printState()
            }

            is UiEvent.Lastnamehanged ->{
                signUpUiState.value = signUpUiState.value.copy(
                    lastname = event.lastname)

                printState()
            }

            is UiEvent.EmailChanged ->{
                signUpUiState.value = signUpUiState.value.copy(
                    email = event.email)

                printState()
            }

            is UiEvent.PasswordChanged ->{
                signUpUiState.value = signUpUiState.value.copy(
                    email = event.password)

                printState()
            }

            is UiEvent.SignUpButtonClicked -> {
                signUp()
            }
        }

    }

    private fun signUp(){
        Log.d(TAG, "Inside_signUp")
        printState()

        validateData()
    }

    private fun validateData() {
        val nameResult = Validator.validateName(
            name = signUpUiState.value.name
        )

        val lastnameResult = Validator.validateLastName(
            lastname = signUpUiState.value.lastname
        )

        val emailResult = Validator.validateEmail(
            email = signUpUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = signUpUiState.value.password
        )

        signUpUiState.value = signUpUiState.value.copy(
            nameError = nameResult.status,
            lastnameError = lastnameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

    }

    private fun printState(){
        Log.d(TAG, "inside_printState")
        Log.d(TAG, signUpUiState.value.toString())
    }
}
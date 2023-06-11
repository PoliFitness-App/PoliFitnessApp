package com.uca.polifitnessapp.ui.signup.ui.viewmodel

import android.util.Patterns
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uca.polifitnessapp.ui.signup.ui.validation.SignUpUiState
import com.uca.polifitnessapp.ui.signup.ui.validation.UiEvent
import kotlinx.coroutines.delay


class SignUpViewModel: ViewModel() {


    // EMAIL
    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    // PASSWORD
    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    // NAME
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    // LASTNAME
    private val _lastname = MutableLiveData("")
    val lastname: LiveData<String> = _lastname

    // CHECKBOX
    private val _checkbox = MutableLiveData<Boolean>()
    val checkbox: LiveData<Boolean> = _checkbox


    // SIGNUP ENABLE

    private val _signupEnable = MutableLiveData<Boolean>()
    val signupEnable: LiveData<Boolean> = _signupEnable

    // IS LOADING

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // ON WRONG INPUT

    private val _isWrongInputEmail = MutableLiveData<Boolean>()
    val isWrongInputEmail: LiveData<Boolean> = _isWrongInputEmail

    private val _isWrongInputName = MutableLiveData<Boolean>()
    val isWrongInputName: LiveData<Boolean> = _isWrongInputName

    private val _isWrongInputLastname = MutableLiveData<Boolean>()
    val isWrongInputLastname: LiveData<Boolean> = _isWrongInputLastname

    private val _isWrongInputPassword = MutableLiveData<Boolean>()
    val isWrongInputPassword: LiveData<Boolean> = _isWrongInputPassword

    private val _isWrongInputChecked = MutableLiveData<Boolean>()
    val isWrongInputChecked: LiveData<Boolean> = _isWrongInputChecked




    // ON LOGIN CHANGED


    fun onSignupChanged(name: String, lastname: String, email: String, password: String, checkbox: Boolean) {
        _name.value = name.trim()
        _lastname.value = lastname.trim()
        _email.value = email.trimEnd()
        _password.value = password.trim()
        _checkbox.value = checkbox



        _signupEnable.value = validateName(name)
        _signupEnable.value = validateLastName(lastname)
        _signupEnable.value = isValidEmail(email)
        _signupEnable.value = isValidPassword(password)
        _signupEnable.value = validateCheckbox(checkbox)


        /*
        Validations for inputs
         */

        _isWrongInputEmail.value = !isValidEmail(email)
        _isWrongInputName.value = !validateName(name)
        _isWrongInputLastname.value = !validateLastName(lastname)
        _isWrongInputPassword.value = !isValidPassword(password)
        _isWrongInputChecked.value = !validateCheckbox(checkbox)


    }

    // IS VALID PASSWORD
    private fun isValidPassword(password: String): Boolean = (!password.isNullOrEmpty() && password.length >= 8)



    // IS VALID EMAIL

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validateEmail(email: String): Boolean = (!email.isNullOrEmpty())

    //VALIDATE NAME

    fun validateName(name: String): Boolean = (!name.isNullOrEmpty() && name.length >= 6)



    //VALIDATE LASTNAME
    fun validateLastName(lastname: String): Boolean = (!lastname.isNullOrEmpty() && lastname.length >= 6)

    //VALIDATE CHECKBOX
    fun validateCheckbox(isChecked: Boolean): Boolean = (isChecked)



    suspend fun onSingupSelected() {
        _isLoading.value = true
        delay(4000)

        _isLoading.value = false
    }

}



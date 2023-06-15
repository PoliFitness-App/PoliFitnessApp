package com.uca.polifitnessapp.ui.signup.viewmodel

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.repositories.CredentialsRepository
import kotlinx.coroutines.delay


class SignUpViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

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

    private var isEnableRegisterButton: MutableState<Boolean> = mutableStateOf(false)


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

    fun onNameChanged(name: String) {
        _name.value = name.trim()

        _signupEnable.value = validateName(name)

        _isWrongInputName.value = !validateName(name)
    }

    fun onLastnameChanged(lastname: String) {
        _lastname.value = lastname.trim()

        _signupEnable.value = validateLastName(lastname)

        _isWrongInputLastname.value = !validateLastName(lastname)
    }

    fun onEmailChanged(email: String) {
        _email.value = email.trimEnd()

        _signupEnable.value = isValidEmail(email)

        _isWrongInputEmail.value = !isValidEmail(email)
    }

    fun onPasswordChanged(password: String) {
        _password.value = password.trim()

        _signupEnable.value = isValidPassword(password)

        _isWrongInputPassword.value = !isValidPassword(password)
    }

    fun onCheckboxChanged(checkbox: Boolean) {
        _checkbox.value = checkbox

        _signupEnable.value = validateCheckbox(checkbox)

        _isWrongInputChecked.value = !validateCheckbox(checkbox)
    }


    // IS VALID PASSWORD
    private fun isValidPassword(password: String): Boolean =
        (!password.isEmpty() && password.length >= 8)

    // IS VALID EMAIL

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validateEmail(email: String): Boolean = (email.isNotEmpty())

    //VALIDATE NAME

    fun validateName(name: String): Boolean = (name.isNotEmpty() && name.length > 1)


    //VALIDATE LASTNAME
    fun validateLastName(lastname: String): Boolean = (lastname.isNotEmpty() && lastname.length > 1)

    //VALIDATE CHECKBOX
    fun validateCheckbox(isChecked: Boolean): Boolean = (isChecked)


    suspend fun onSingupSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

    // ---
    // Companion object
    // ---

    // Factory of the view model
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                SignUpViewModel(app.credentialRepository)
            }
        }
    }

}


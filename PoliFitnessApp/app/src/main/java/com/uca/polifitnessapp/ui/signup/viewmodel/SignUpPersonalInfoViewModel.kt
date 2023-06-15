package com.uca.polifitnessapp.ui.signup.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.repositories.CredentialsRepository
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel
import com.uca.polifitnessapp.ui.signup.validation.SignUpUiStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpPersonalInfoViewModel(
    private val repository: CredentialsRepository
): ViewModel() {

    // GENDER
    private val _gender = MutableLiveData("")
    val gender: LiveData<String> = _gender

    // BIRTHDATE
    private val _birthdate = MutableLiveData("")
    val birthdate: LiveData<String> = _birthdate

    // WEIGHT
    private val _weight = MutableLiveData("")
    val weight: LiveData<String> = _weight

    // HEIGHT
    private val _height = MutableLiveData("")
    val height: LiveData<String> = _height

    // WAIST
    private val _waist = MutableLiveData("")
    val waist: LiveData<String> = _waist

    // HIP
    private val _hip = MutableLiveData("")
    val hip: LiveData<String> = _hip

    // ---
    // Status
    // ---

    // IS LOADING
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // SIGNUP ENABLE
    private val _signupEnable = MutableLiveData<Boolean>()
    val signupEnable: LiveData<Boolean> = _signupEnable

    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)

    private val _status = MutableLiveData<SignUpUiStatus>(SignUpUiStatus.Resume)
    val status: LiveData<SignUpUiStatus>
        get() = _status

    // ---
    // Functions
    // ---

    private fun register(gender: String, birthdate: String, weight: String, height: String, waistP: String, hipP: String) {
        // Create a coroutine to call the register function from the repository
        // and inside the coroutine set the value of the status

    }

    fun onRegisterChange() {
        // Validate the data and call the register function

        if(!validateData()){
            _signupEnable.value = false
            return
        }
        register(gender.value!! ,birthdate.value!!, weight.value!!, height.value!!, waist.value!!, hip.value!!)
        _signupEnable.value = true
        return
    }

    private fun validateData(): Boolean {
        when {
            gender.value.isNullOrEmpty() -> return false
            birthdate.value.isNullOrEmpty() -> return false
            weight.value.isNullOrEmpty() -> return false
            height.value.isNullOrEmpty() -> return false
            waist.value.isNullOrEmpty() -> return false
            hip.value.isNullOrEmpty() -> return false

        }
        return true
    }

    private fun shouldEnabledRegisterButton() {
        isEnabledRegisterButton.value =
            gender.value?.isNotEmpty() ?: false
                    && birthdate.value?.isNotEmpty() ?: false
                    && weight.value?.isNotEmpty() ?: false
                    && height.value?.isNotEmpty() ?: false
                    && waist.value?.isNotEmpty() ?: false
                    && hip.value?.isNotEmpty() ?: false

    }

    fun clearStatus() {
        _status.value = SignUpUiStatus.Resume
    }

    suspend fun onSingupSelected() {
    }

    // ---
    // Companion object
    //

    // Factory of the view model
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                SignUpPersonalInfoViewModel(app.credentialRepository)
            }
        }
    }

}

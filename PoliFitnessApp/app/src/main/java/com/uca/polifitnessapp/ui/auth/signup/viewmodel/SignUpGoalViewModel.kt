package com.uca.polifitnessapp.ui.auth.signup.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.repositories.CredentialsRepository
import com.uca.polifitnessapp.ui.auth.signup.validation.SignUpUiStatus
import kotlinx.coroutines.launch

class SignUpGoalViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // ---
    // Variables
    // ---

    // username
    var username by mutableStateOf(TextFieldState())
        private set

    // lastname
    var lastname by mutableStateOf(TextFieldState())
        private set

    // email
    var email by mutableStateOf(TextFieldState())
    private set

    // password
    var password by mutableStateOf(TextFieldState())
        private set

    // gender
    var gender by mutableStateOf(TextFieldState())
        private set

    // date of birth
    var dateOfBirth by mutableStateOf(TextFieldState())
        private set

    // weight
    var weight by mutableStateOf(TextFieldState())
        private set

    // height
    var height by mutableStateOf(TextFieldState())
        private set

    // waistP
    var waistP by mutableStateOf(TextFieldState())
        private set

    // hipP
    var hipP by mutableStateOf(TextFieldState())
        private set

    // Approach
    var approach by mutableStateOf(TextFieldState())
        private set

    // CheckBox
    var checkBox by mutableStateOf(false)
        private set

    var weightUnitState by mutableStateOf("KG")

    // Is enable?
    private val _isEnabled = MutableLiveData(false)
    val isEnabled: MutableLiveData<Boolean>
        get() = _isEnabled

    // is checked?
    private val _isChecked = MutableLiveData(false)
    val isChecked: MutableLiveData<Boolean>
        get() = _isChecked

    // is signUp_1 enabled?
    private val _isSignUpButton1 = MutableLiveData(false)
    val isSignUpButton1: MutableLiveData<Boolean>
        get() = _isSignUpButton1

    // is signUp_1 enabled?
    private val _isSignUpButton2 = MutableLiveData(false)
    val isSignUpButton2: MutableLiveData<Boolean>
        get() = _isSignUpButton2

    // ---
    // Status
    // --

    private val _status = MutableLiveData<SignUpUiStatus>(SignUpUiStatus.Resume)
    val status: MutableLiveData<SignUpUiStatus>
        get() = _status

    // ---
    // Functions
    // ---

    // Signup function -> Calls the repository to signup, and updates the status
    private fun signUp(
        username: String,
        lastname: String,
        email: String,
        password: String,
        imc: Float,
        icc: Float,
        gender: Boolean,
        dateOfBirth: String,
        weight: Float,
        height: Float,
        waistP: Float,
        hipP: Float,
        approach: String,
    ) {
        viewModelScope.launch {
            _status.postValue(
                // Calls the repository to signup
                when (val response =
                    repository.register(
                        username,
                        lastname,
                        email,
                        password,
                        imc,
                        icc,
                        gender,
                        dateOfBirth,
                        weight,
                        height,
                        waistP,
                        hipP,
                        approach
                    )
                ) {
                    // If there is an error, it returns an error
                    is ApiResponse.Error -> SignUpUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> SignUpUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> SignUpUiStatus.Success
                }
            )
        }
    }

    // On sign up function -> Calls the signup function
    fun onSignUp() {
        // Gender
        val genderU: Boolean = gender.value == "Masculino"

        val weightU = if (weightUnitState == "KG") {
            weight.value.toFloat()
        } else {
            weight.value.toFloat() / 2.20462f
        }

        val imc = weightU / (height.value.toFloat() * height.value.toFloat())
        val icc = waistP.value.toFloat() / hipP.value.toFloat()

        // If the password is not empty, it calls the signup function
        signUp(
            username.value,
            lastname.value,
            email.value,
            password.value,
            imc,
            icc,
            genderU,
            dateOfBirth.value,
            weightU,
            height.value.toFloat(),
            waistP.value.toFloat(),
            hipP.value.toFloat(),
            approach.value
        )
    }

    // ---
    // On change functions
    // ---

    // On username change function -> Updates the username variable
    fun onUsernameChange(usernameU: String) {
        // If the username is empty, it returns an error
        val error = validateUsername(usernameU)

        // If the username is not empty, it updates the username variable
        username = username.copy(
            value = usernameU,
            error = error
        )
    }

    // Validate Username
    private fun validateUsername(name: String): String = if (name.isNotEmpty() && name.length > 1)  "" else "Por favor, ingrese un nombre válido"


    // On lastname change function -> Updates the lastname variable
    fun onLastnameChange(lastnameU: String) {
        // If the lastname is empty, it returns an error
        val error = validateLastName(lastnameU)

        // If the lastname is not empty, it updates the lastname variable
        lastname = lastname.copy(
            value = lastnameU,
            error = error
        )
    }

    // Validate lastname
    private fun validateLastName(lastname: String): String = if (lastname.isNotEmpty() && lastname.length > 1) "" else "Por favor, ingrese un apellido válido"


    // On email change function -> Updates the email variable
    fun onEmailChange(emailU: String) {
        // If the email is empty, it returns an error
        val error = isValidEmail(emailU)

        // If the email is not empty, it updates the email variable
        email = email.copy(
            value = emailU,
            error = error
        )
    }

    // Valid Email
    private fun isValidEmail(email: String): String = if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) "" else "Por favor, ingrese un email válido (example@example.com)"

    // On password change function -> Updates the password variable
    fun onPasswordChange(passwordU: String) {
        // If the email is empty, it returns an error
        val error = isValidPassword(passwordU)

        // If the email is not empty, it updates the email variable
        password = password.copy(
            value = passwordU,
            error = error
        )
    }
    // Valid Password
    private fun isValidPassword(password: String): String = if (Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$").matches(password)) "" else "La contraseña debe de tener entre 8 y 32 chars, y al menos 1 M, 1 m y 1 #"

    // On weight change function -> Updates the weight variable
    fun onWeightChange(weightU: String) {
        // If the weight is empty, it returns an error
        val error = isValidWeight(weightU)

        // If the weight is not empty, it updates the email variable
        weight = weight.copy(
            value = weightU,
            error = error
        )
    }
    private fun isValidWeight(weight: String): String = if (weight.isNotEmpty() && weight.length > 1) "" else "Por favor, ingrese un peso válido (kg/lb)"

    // On height change function -> Updates the height variable
    fun onHeightChange(heightU: String) {
        // If the weight is empty, it returns an error
        val error = isValidHeight(heightU)

        // If the weight is not empty, it updates the email variable
        height = height.copy(
            value = heightU,
            error = error
        )
    }
    // Valid Height
    private fun isValidHeight(value: String): String = if (value.isNotEmpty() && value.length > 1) "" else "Por favor, ingrese una altura válida (mts)"

    // On waistP change function -> Updates the waistP variable
    fun onWaistPChange(waistPU: String) {
        // If the weight is empty, it returns an error
        val error = isValidWaistP(waistPU)

        // If the weight is not empty, it updates the email variable
        waistP = waistP.copy(
            value = waistPU,
            error = error
        )
    }
    // Valid waistP
    private fun isValidWaistP(waist: String): String = if (waist.isNotEmpty() && waist.length > 1) "" else "Por favor, ingrese un valor valido (cm)"

    // On hipP change function -> Updates the hipP variable
    fun onHipPChange(hipPU: String) {
        // If the weight is empty, it returns an error
        val error = isValidHipP(hipPU)

        // If the weight is not empty, it updates the email variable
        hipP = waistP.copy(
            value = hipPU,
            error = error
        )

        _isSignUpButton2.value =
            isValidWeight(weight.error).isNotEmpty() &&
                    isValidHeight(height.error).isNotEmpty() &&
                    isValidWaistP(waistP.error).isNotEmpty() &&
                    isValidHipP(hipP.error).isNotEmpty()
    }

    private fun isValidHipP(hip: String): String = if (hip.isNotEmpty() && hip.length > 1) "" else "Por favor, ingrese un valor valido (cm)"

    // On gender change function -> Updates the gender value
    fun onGenderChange(genderU: String) {
        gender = gender.copy(
            value = genderU
        )
    }

    // On age change function -> Updates the age variable
    fun onAgeChange(ageU: String) {
        // If the age is not empty, it updates the age variable
        dateOfBirth =  gender.copy(
            value = ageU
        )
    }

    fun onCheckboxChanged(checkboxU: Boolean) {
        _isChecked.value = !validateCheckbox(checkboxU)
        checkBox = checkboxU

        _isSignUpButton1.value =
            validateCheckbox(checkboxU) &&
                    validateUsername(username.error).isNotEmpty() &&
                    validateLastName(lastname.error).isNotEmpty() &&
                    isValidEmail(email.error).isNotEmpty() &&
                    isValidPassword(password.error).isNotEmpty()
    }

    private fun validateCheckbox(isChecked: Boolean): Boolean = (isChecked)

    // On approach function -> Updates the approach variable
    fun onApproachChange(approachU: String) {
        // If the approach is not empty, it updates the approach variable
        approach = approach.copy(
            value = approachU
        )
        // It also updates the is enabled variable
        _isEnabled.value = true
    }

    fun changeUnit() {
        if (weightUnitState == "KG")
            weightUnitState = "LB"
        else
            weightUnitState = "KG"
    }

    // ---
    // Auxiliary functions
    // ---

    fun clearData() {
        username = username.copy(value = "", error = "")
        lastname = lastname.copy(value = "", error = "")
        email = email.copy(value = "", error = "")
        password = password.copy(value = "", error = "")
        weight = weight.copy(value = "", error = "")
        height = height.copy(value = "", error = "")
        waistP = waistP.copy(value = "", error = "")
        hipP = hipP.copy(value = "", error = "")
        checkBox = false
        approach = approach.copy(value = "", error = "")
    }

    fun clearStatus() {
        _status.value = SignUpUiStatus.Resume
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
                SignUpGoalViewModel(app.credentialRepository)
            }
        }
    }
}


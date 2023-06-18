package com.uca.polifitnessapp.ui.signup.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.repositories.CredentialsRepository
import com.uca.polifitnessapp.ui.login.ui.LoginUiStatus
import com.uca.polifitnessapp.ui.signup.validation.SignUpUiStatus
import kotlinx.coroutines.launch

class SignUpGoalViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // ---
    // Variables
    // ---

    // username
    var username = MutableLiveData("")

    // lastname
    var lastname = MutableLiveData("")

    // email
    var email = MutableLiveData("")

    // password
    var password = MutableLiveData("")

    // gender
    var gender = MutableLiveData("")

    // date of birth
    var dateOfBirth = MutableLiveData("")

    // weight
    var weight = MutableLiveData("")

    // height
    var height = MutableLiveData("")

    // waistP
    var waistP = MutableLiveData("")

    // hipP
    var hipP = MutableLiveData("")

    // Approach
    var approach = MutableLiveData("")

    // CheckBox
    var checkBox = MutableLiveData(false)

    // Is enable?
    private val _isEnabled = MutableLiveData(false)
    val isEnabled: MutableLiveData<Boolean>
        get() = _isEnabled

    // is valid username?
    private val _isValidUsername = MutableLiveData(false)
    val isValidUsername: MutableLiveData<Boolean>
        get() = _isValidUsername

    // is valid lastname?
    private val _isValidLastname = MutableLiveData(false)
    val isValidLastname: MutableLiveData<Boolean>
        get() = _isValidLastname

    // is valid email?
    private val _isValidEmail = MutableLiveData(false)
    val isValidEmail: MutableLiveData<Boolean>
        get() = _isValidEmail

    // is valid password?
    private val _isValidPassword = MutableLiveData(false)
    val isValidPassword: MutableLiveData<Boolean>
        get() = _isValidPassword

    // is valid weight?
    private val _isValidWeight = MutableLiveData(false)
    val isValidWeight: MutableLiveData<Boolean>
        get() = _isValidWeight

    // is valid height?
    private val _isValidHeight = MutableLiveData(false)
    val isValidHeight: MutableLiveData<Boolean>
        get() = _isValidHeight

    // is valid waistP?
    private val _isValidWaistP = MutableLiveData(false)
    val isValidWaistP: MutableLiveData<Boolean>
        get() = _isValidWaistP

    // is valid hipP?
    private val _isValidHipP = MutableLiveData(false)
    val isValidHipP: MutableLiveData<Boolean>
        get() = _isValidHipP

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
        val imc: Float =
            weight.value!!.toFloat() / (height.value!!.toFloat() * height.value!!.toFloat())
        val icc: Float = waistP.value!!.toFloat() / hipP.value!!.toFloat()


        // If the password is not empty, it calls the signup function
        signUp(
            username.value!!,
            lastname.value!!,
            email.value!!,
            password.value!!,
            imc,
            icc,
            genderU,
            dateOfBirth.value!!,
            weight.value!!.toFloat(),
            height.value!!.toFloat(),
            waistP.value!!.toFloat(),
            hipP.value!!.toFloat(),
            approach.value!!
        )
    }

    // ---
    // On change functions
    // ---

    // On username change function -> Updates the username variable
    fun onUsernameChange(usernameU: String) {
        // If the username is empty, it returns an error
        _isValidUsername.value = !validateUsername(usernameU)

        // If the username is not empty, it updates the username variable
        username.value = usernameU
    }

    // Validate Username
    private fun validateUsername(name: String): Boolean = (name.isNotEmpty() && name.length > 1)


    // On lastname change function -> Updates the lastname variable
    fun onLastnameChange(lastnameU: String) {
        // If the lastname is empty, it returns an error
        _isValidLastname.value = !validateLastName(lastnameU)
        // If the lastname is not empty, it updates the lastname variable
        lastname.value = lastnameU
    }

    // Validate lastname
    private fun validateLastName(lastname: String): Boolean =
        (lastname.isNotEmpty() && lastname.length > 1)


    // On email change function -> Updates the email variable
    fun onEmailChange(emailU: String) {
        // If the email is empty, it returns an error
        _isValidEmail.value = !isValidEmail(email.value!!)
        //then
        email.value = emailU.trimEnd()
    }

    // Valid Email
    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    // On password change function -> Updates the password variable
    fun onPasswordChange(passwordU: String) {

        // If the password is valid?
        _isValidPassword.value = !isValidPassword(password.value!!)

        println(_isValidPassword.value)
        // Set te value
        password.value = passwordU
    }

    // Valid Password
    private fun isValidPassword(password: String): Boolean =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$").matches(password)

    // On weight change function -> Updates the weight variable
    fun onWeightChange(weightU: String) {
        // If the weight is empty, it returns an error
        _isValidWeight.value = isValidWeight(weight.value!!)

        // If the weight is not empty, it updates the weight variable
        weight.value = weightU
    }

    private fun isValidWeight(weight: String): Boolean {
        val numericRegex = Regex("^\\d+(\\.\\d+)?$")
        val maxWeight = 1000.0
        return numericRegex.matches(weight) && weight.toDouble() > 0 && weight.toDouble() <= maxWeight
    }

    // On height change function -> Updates the height variable
    fun onHeightChange(heightU: String) {
        // If the height is empty, it returns an error
        _isValidHeight.value = isValidHeight(height.value!!)
        // If the height is not empty, it updates the height variable
        height.value = heightU
    }

    // Valid Height
    private fun isValidHeight(value: String): Boolean {
        val numericRegex = Regex("^\\d+(\\.\\d+)?$")
        val maxWeight = 3.0
        return numericRegex.matches(value) && value.toDouble() > 0 && value.toDouble() <= maxWeight
    }

    // On waistP change function -> Updates the waistP variable
    fun onWaistPChange(waistPU: String) {
        // If the waistP is empty, it returns an error
        _isValidWaistP.value = isValidWaistP(waistP.value!!)
        // If the waistP is not empty, it updates the waistP variable
        waistP.value = waistPU
    }

    // Valid waistP
    private fun isValidWaistP(waist: String): Boolean {
        val numericRegex = Regex("^\\d+(\\.\\d+)?$")
        val maxWaistP = 200.0
        return numericRegex.matches(waist) && waist.toDouble() > 0 && waist.toDouble() <= maxWaistP
    }

    // On hipP change function -> Updates the hipP variable
    fun onHipPChange(hipPU: String) {
        // If the hipP is empty, it returns an error
        _isValidHipP.value = isValidHipP(hipP.value!!)
        // If the hipP is not empty, it updates the hipP variable
        hipP.value = hipPU
        _isSignUpButton2.value =
            isValidWeight(weight.value!!) &&
                    isValidHeight(height.value!!) &&
                    isValidWaistP(waistP.value!!) &&
                    isValidHipP(hipP.value!!)
    }

    private fun isValidHipP(hip: String): Boolean {
        val numericRegex = Regex("^\\d+(\\.\\d+)?$")
        val maxHipP = 200.0
        return numericRegex.matches(hip) && hip.toDouble() > 0 && hip.toDouble() <= maxHipP
    }

    // On gender change function -> Updates the gender value
    fun onGenderChange(genderU: String) {
        gender.value = genderU
    }

    // On age change function -> Updates the age variable
    fun onAgeChange(ageU: String) {
        // If the age is not empty, it updates the age variable
        dateOfBirth.value = ageU
    }

    fun onCheckboxChanged(checkboxU: Boolean) {
        _isChecked.value = !validateCheckbox(checkBox.value!!)
        checkBox.value = checkboxU
        _isSignUpButton1.value =
            validateCheckbox(checkboxU) &&
                    validateUsername(username.value!!) &&
                    validateLastName(lastname.value!!) &&
                    isValidEmail(email.value!!) &&
                    isValidPassword(password.value!!)
    }

    private fun validateCheckbox(isChecked: Boolean): Boolean = (isChecked)

    // On approach function -> Updates the approach variable
    fun onApproachChange(approachU: String) {
        // If the approach is not empty, it updates the approach variable
        approach.value = approachU
        // It also updates the is enabled variable
        _isEnabled.value = true
    }

    // ---
    // Auxiliary functions
    // ---

    fun clearData() {
        approach.value = ""
        username.value = ""
        lastname.value = ""
        email.value = ""
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


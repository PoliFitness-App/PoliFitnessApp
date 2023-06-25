package com.uca.polifitnessapp.ui.user.viewmodel

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
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.repositories.CredentialsRepository
import com.uca.polifitnessapp.ui.auth.signup.viewmodel.TextFieldState
import com.uca.polifitnessapp.ui.user.status.EditProfileUiStatus
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // --
    // Text fields variables
    // --

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

    var weightUnitState by mutableStateOf("KG")

    // ---
    // States
    // ---

    // Is button enable?
    private val _isEnabled = MutableLiveData(false)
    val isEnabled: MutableLiveData<Boolean>
        get() = _isEnabled

    // status
    private val _status = MutableLiveData<EditProfileUiStatus>(EditProfileUiStatus.Resume)
    val status: MutableLiveData<EditProfileUiStatus>
        get() = _status

    // ---
    // We set the functions
    // ---

    fun onWeightChange(weightU: String) {
        // If the weight is empty, it returns an error
        val error = isValidWeight(weightU)

        // If the weight is not empty, it updates the email variable
        weight = weight.copy(
            value = weightU,
            error = error
        )
    }

    private fun isValidWeight(weight: String): String =
        if (weight.isNotEmpty() && weight.length > 1) "" else "Por favor, ingrese un peso válido (kg/lb)"

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
    private fun isValidHeight(value: String): String =
        if (value.isNotEmpty() && value.length > 1) "" else "Por favor, ingrese una altura válida (mts)"

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
    private fun isValidWaistP(waist: String): String =
        if (waist.isNotEmpty() && waist.length > 1) "" else "Por favor, ingrese un valor valido (cm)"

    // On hipP change function -> Updates the hipP variable
    fun onHipPChange(hipPU: String) {
        // If the weight is empty, it returns an error
        val error = isValidHipP(hipPU)

        // If the weight is not empty, it updates the email variable
        hipP = waistP.copy(
            value = hipPU,
            error = error
        )

        _isEnabled.value =
            isValidWeight(weight.value).isEmpty() &&
                    isValidHeight(height.value).isEmpty() &&
                    isValidWaistP(waistP.value).isEmpty() &&
                    isValidHipP(hipP.value).isEmpty()
    }

    private fun isValidHipP(hip: String): String =
        if (hip.isNotEmpty() && hip.length > 1) "" else "Por favor, ingrese un valor valido (cm)"

    fun onApproachChange(approachU: String) {
        // If the approach is not empty, it updates the approach variable
        approach = approach.copy(
            value = approachU
        )
    }

    // On Update
    fun onUpdate(user: UserModel) {

        val weightU = if (weightUnitState == "KG") {
            weight.value.toFloat()
        } else {
            weight.value.toFloat() / 2.20462f
        }

        val imc = weightU / (height.value.toFloat() * height.value.toFloat())
        val icc = waistP.value.toFloat() / hipP.value.toFloat()

        // we set the data
        user.weight = weight.value.toFloat()
        user.height = height.value.toFloat()
        user.waistP = waistP.value.toFloat()
        user.hipP = hipP.value.toFloat()
        user.approach = approach.value
        user.imc = imc
        user.icc = icc
        // we update the data
        updateData(user)
    }

    private fun updateData(user: UserModel) {
        println(user)
        // we update the data
        viewModelScope.launch {
            _status.postValue(
                when (val response = repository.updateUser(user)) {
                    is ApiResponse.Error -> EditProfileUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> EditProfileUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> EditProfileUiStatus.Success(response.data)
                }
            )
        }
    }

    // ---
    // We set the auxiliary functions
    // ---

    // Clear data function
    fun clearData() {
        weight = weight.copy(value = "", error = "")
        height = height.copy(value = "", error = "")
        waistP = waistP.copy(value = "", error = "")
        hipP = hipP.copy(value = "", error = "")
        approach = approach.copy(value = "", error = "")
    }

    // Clear status function
    fun clearStatus() {
        _status.value = EditProfileUiStatus.Resume
    }

    fun changeUnit() {
        if (weightUnitState == "KG")
            weightUnitState = "LB"
        else
            weightUnitState = "KG"
    }

    // ---
    // Factory
    // ---

    // Companion object to set the factory
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                EditProfileViewModel(app.credentialRepository)
            }
        }
    }
}

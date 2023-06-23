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
import com.uca.polifitnessapp.ui.main.calculator.ui.ValueState
import com.uca.polifitnessapp.ui.user.status.EditProfileUiStatus
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // --
    // Text fields variables
    // --

    // weight
    var weight = MutableLiveData("")

    // height
    var height = MutableLiveData("")

    // waistP
    var waistP = MutableLiveData("")

    // hipP
    var hipP = MutableLiveData("")

    var weightUnitState by mutableStateOf("KG")

    var approachState by mutableStateOf(ValueState())

    fun updateApproach(it: String) {
        approachState = approachState.copy(value = it, error = null)


    }

    // ---
    // States
    // ---

    // Weight?
    private val _isValidWeight = MutableLiveData(false)
    val isValidWeight: MutableLiveData<Boolean>
        get() = _isValidWeight

    // Height?
    private val _isValidHeight = MutableLiveData(false)
    val isValidHeight: MutableLiveData<Boolean>
        get() = _isValidHeight

    // WaistP?
    private val _isValidWaistP = MutableLiveData(false)
    val isValidWaistP: MutableLiveData<Boolean>
        get() = _isValidWaistP

    // HipP?
    private val _isValidHipP = MutableLiveData(false)
    val isValidHipP: MutableLiveData<Boolean>
        get() = _isValidHipP

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

    // On Field Change
    fun onFieldChange(weightU: String, heightU: String, waistPU: String, hipPU: String) {
        // verify if the data is valid
        // if the data is valid

        // we set the data
        weight.value = weightU.trim()
        height.value = heightU.trim()
        waistP.value = waistPU.trim()
        hipP.value = hipPU.trim()

        // we set the state of the button
        _isEnabled.value = validateData()
    }

    // On Update
    fun onUpdate(user: UserModel) {

        // we set the data
        user.weight = weight.value!!.toFloat()
        user.height = height.value!!.toFloat()
        user.waistP = waistP.value!!.toFloat()
        user.hipP = hipP.value!!.toFloat()

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

    private fun validateData(): Boolean {
        when {
            weight.value.toString().isEmpty() -> return false
            height.value.toString().isEmpty() -> return false
            waistP.value.toString().isEmpty() -> return false
            hipP.value.toString().isEmpty() -> return false
        }
        return true
    }


    // ---
    // We set the auxiliary functions
    // ---

    // Clear data function
    fun clearData() {
        // We clear the data
        weight.value = ""
        height.value = ""
        waistP.value = ""
        hipP.value = ""
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

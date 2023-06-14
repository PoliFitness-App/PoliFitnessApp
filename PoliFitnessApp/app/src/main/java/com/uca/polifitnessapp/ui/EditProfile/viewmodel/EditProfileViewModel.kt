package com.uca.polifitnessapp.ui.EditProfile.viewmodel

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
import com.uca.polifitnessapp.ui.EditProfile.ui.EditProfileUiStatus
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: CredentialsRepository
) : ViewModel() {

    // --
    // Text fields variables
    // --

    // weght
    var weight = MutableLiveData(0F)

    // height
    var height = MutableLiveData(0F)

    // waistP
    var waistP = MutableLiveData("")

    // hipP
    var hipP = MutableLiveData("")

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
    fun onFieldChange(weightU: Float, heightU: Float, waistPU: String, hipPU: String) {
        // verify if the data is valid
        // if the data is valid
        if (validateData()) {
            // we set the data
            weight.value = weightU
            height.value = heightU
            waistP.value = waistPU
            hipP.value = hipPU
        }

        // we set the state of the button
        _isEnabled.value = validateData()
    }

    // On Update
    fun onUpdate(user: UserModel) {

        // we set the data
        user.weight = weight.value!!
        user.height = height.value!!
        user.waistP = waistP.value!!
        user.hipP = hipP.value!!

        // we update the data
        updateData(user)
    }

    private fun updateData(user: UserModel) {
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
            waistP.value.isNullOrEmpty() -> return false
            hipP.value.isNullOrEmpty() -> return false
        }
        return true
    }

    // We set the functions
    // Clear data function
    fun clearData() {
        // We clear the data
        weight.value = 0F
        height.value = 0F
        waistP.value = ""
        hipP.value = ""
    }

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

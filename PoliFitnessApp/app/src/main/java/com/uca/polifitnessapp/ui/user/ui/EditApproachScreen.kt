package com.uca.polifitnessapp.ui.user.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.auth.signup.ui.ConfirmGoalButton
import com.uca.polifitnessapp.ui.auth.signup.ui.MainFunction
import com.uca.polifitnessapp.ui.auth.signup.ui.SignUpGoalText
import com.uca.polifitnessapp.ui.user.status.EditProfileUiStatus
import com.uca.polifitnessapp.ui.user.viewmodel.EditProfileViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun EditApproachScreen(
    viewModel: EditProfileViewModel,
    userViewModel: UserViewModel,
    onUpdateSuccess: () -> Unit,
) {
    // ---
    // Variables
    // ---
    // Is wrong ?
    val isEnabled: Boolean by viewModel.isEnabled.observeAsState(initial = false)

    // ---
    // Auxiliary variables
    // ---
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrollState = rememberScrollState()

    val app: PoliFitnessApplication = LocalContext.current.applicationContext as PoliFitnessApplication

    // ---
    // viewModel
    // ---
    // Get the user from the view model

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ---
        // Tittle
        // ---
        SignUpGoalText()
        // ---
        // Carousel
        // ---
        MainFunction(){
            // Set the approach on the view model
            viewModel.onApproachChange(it)
        }
        // ---
        // Button
        // ---
        ConfirmGoalButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            confirmGoal = isEnabled,
        ) {
            coroutineScope.launch {
                viewModel.status.observe(lifecycleOwner) { status ->
                    handleUiStatus(
                        status,
                        context,
                        viewModel,
                        onUpdateSuccess
                    )
                }
                viewModel.onUpdate(
                    userViewModel.user
                )
            }
        }
    }
}

// ---
// Handle status changes
//
fun handleUiStatus(
    status: EditProfileUiStatus,
    context: Context,
    viewModel: EditProfileViewModel,
    onSignUpSuccess: () -> Unit,
) {
    when (status) {
        is EditProfileUiStatus.Error -> {
            Toast.makeText(context, "Error de conexion", Toast.LENGTH_SHORT).show()
        }
        is EditProfileUiStatus.ErrorWithMessage -> {
            Toast.makeText(context, status.message, Toast.LENGTH_SHORT).show()
        }
        is EditProfileUiStatus.Success -> {
            Toast.makeText(context, "Has actualizado tu cuenta con exito!", Toast.LENGTH_SHORT).show()
            onSignUpSuccess()
            viewModel.clearData()
            viewModel.clearStatus()
        }
        else -> {}
    }
}

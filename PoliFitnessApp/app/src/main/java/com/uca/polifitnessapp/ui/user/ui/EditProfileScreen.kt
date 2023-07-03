package com.uca.polifitnessapp.ui.user.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.auth.signup.ui.TextFieldIcon
import com.uca.polifitnessapp.ui.navigation.components.BackButton
import com.uca.polifitnessapp.ui.user.viewmodel.EditProfileViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel


@Composable
fun EditProfileScreen(
    userViewModel: UserViewModel,
    viewModel: EditProfileViewModel,
    userId: String,
    onBackPress: () -> Unit,
    onNavigate: () -> Unit
) {
    LaunchedEffect(userId) {
        userViewModel.fetchUserById(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .verticalScroll(rememberScrollState())
            .padding(30.dp, 0.dp, 30.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        BackButton(
            modifier = Modifier
                .align(Alignment.Start),
            onBackPress
        )
        HeaderImage()
        EditProfileText()
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldSection(
            viewModel,
            userViewModel,
            onNavigate
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}


// -----------

// HEADER IMAGE

// ------------


@Composable
fun HeaderImage() {
    Box(
        modifier = Modifier
            .size(250.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.editprofileimg),
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentDescription = "Imagen "
        )
    }

}

// -----------

// HEADER TEXT

// ------------

@Composable
fun EditProfileText() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hey!")

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = "¿Es hora de un cambio?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.fireimg),
                contentDescription = "Imagen fuego"
            )
        }

    }
}

@Composable()
fun TextFieldSection(
    viewModel: EditProfileViewModel,
    userViewModel: UserViewModel,
    onNavigate: () -> Unit
) {

    val isValidForm by viewModel.isEnabled.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ---
        // WeightField
        // ---

        TextFieldIcon(
            Modifier,
            icon = Icons.Outlined.MonitorWeight,
            state = viewModel.weight,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            iconText = viewModel.weightUnitState,
            labelText = "Peso",
            onValueChange = viewModel::onWeightChange
        ) {
            viewModel.changeUnit()
        }

        // ---
        // HeightField
        // ---

        TextFieldIcon(
            Modifier,
            icon = Icons.Outlined.Height,
            state = viewModel.height,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            iconText = "M",
            labelText = "Altura",
            onValueChange = viewModel::onHeightChange
        ) {
        }

        // ---
        // WaistField
        // ---

        TextFieldIcon(
            Modifier,
            icon = Icons.Outlined.Straighten,
            state = viewModel.waistP,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            iconText = "CM",
            labelText = "Perimetro de cintura",
            onValueChange = viewModel::onWaistPChange
        ) {
        }

        // ---
        // HipField
        // ---

        TextFieldIcon(
            Modifier,
            icon = Icons.Outlined.Straighten,
            state = viewModel.hipP,
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number,
            iconText = "CM",
            labelText = "Perimetro de cadera",
            onValueChange = viewModel::onHipPChange
        ) {
        }

        Spacer(modifier = Modifier.height(15.dp))
        // ---
        // Button
        // ---
        SaveButton(
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            ),
            isValidForm
        ) {
            onNavigate()
        }
    }
}

@Composable
fun SaveButton(
    modifier: Modifier,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        enabled = isEnabled,
        modifier = modifier
            .width(315.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF034189)
        ),
    )
    {
        Text(
            text = "Siguiente",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

package com.uca.polifitnessapp.ui.user.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.user.viewmodel.EditProfileViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel




@Composable
fun EditProfileScreen(
    userViewModel: UserViewModel,
    viewModel: EditProfileViewModel,
    userId: String,
    onBackPress: () -> Unit,
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
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.Start),
            onBackPress
        )
        HeaderImage()
        EditProfileText()
        Spacer(modifier = Modifier.height(10.dp))
        combine(
            viewModel,
            userViewModel
        )
        Spacer(modifier = Modifier.height(50.dp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightField(
    modifier: Modifier,
    weightVM: String,
    isValidWeight: Boolean,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = weightVM,
        onValueChange = {
            onTextFieldChanged(it)
        },
        isError = isValidWeight,
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.MonitorWeight,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Peso",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(260.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)
        )
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun heightField(
    modifier: Modifier,
    heightVM: String,
    isValidHeight: Boolean,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = heightVM,
        onValueChange = {
            onTextFieldChanged(it)
        },
        isError = isValidHeight,
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Height,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Altura",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(260.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)
        )
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun waistField(
    modifier: Modifier,
    waistP: String,
    isValidWaist: Boolean,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = waistP,
        onValueChange = {
            onTextFieldChanged(it)
        },
        isError = isValidWaist,
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Straighten,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Perimetro de cintura",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(260.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)
        )
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun hipField(
    modifier: Modifier,
    hipP: String,
    isValidHip: Boolean,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = hipP,
        onValueChange = {
            onTextFieldChanged(it)
        },
        isError = isValidHip,
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Straighten,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Perimetro de cadera",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(260.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        )
    )

}

@Composable
fun kgicon() {
    ElevatedCard(
        modifier = Modifier
            .height(74.dp)
            .size(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF034189))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "KG", color = Color.White)
        }

    }
}

@Composable
fun cmicon() {
    ElevatedCard(
        modifier = Modifier
            .height(74.dp)
            .size(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF034189))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "CM", color = Color.White)
        }
    }
}

@Composable()
fun combine(
    viewModel: EditProfileViewModel,
    userViewModel: UserViewModel
) {

    // Set the variable to remember the state of the text's field
    // weight, height, waistP and hipP
    val weight: String by viewModel.weight.observeAsState(initial = "")
    val height: String by viewModel.height.observeAsState(initial = "")
    val waistP: String by viewModel.waistP.observeAsState(initial = "")
    val hipP: String by viewModel.hipP.observeAsState(initial = "")

    // Set the variable to remember the state
    // isValidWeight, isValidHeight, isValidWaistP and isValidHipP
    val isValidWeight by viewModel.isValidWeight.observeAsState(initial = false)
    val isValidHeight by viewModel.isValidHeight.observeAsState(initial = false)
    val isValidWaistP by viewModel.isValidWaistP.observeAsState(initial = false)
    val isValidHipP by viewModel.isValidHipP.observeAsState(initial = false)
    val isValidForm by viewModel.isEnabled.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            WeightField(
                modifier = Modifier.align(Alignment.CenterVertically),
                weight,
                isValidWeight
            ) {
                viewModel.onFieldChange(it, height, waistP, hipP)
            }

            kgicon()

        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            heightField(
                modifier = Modifier.align(Alignment.CenterVertically),
                height,
                isValidHeight
            ) {
                viewModel.onFieldChange(weight, it, waistP, hipP)
            }
            cmicon()

        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            waistField(
                modifier = Modifier.align(Alignment.CenterVertically),
                waistP,
                isValidWaistP
            ) {
                viewModel.onFieldChange(weight, height, it, hipP)
            }
            cmicon()

        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            hipField(
                modifier = Modifier.align(Alignment.CenterVertically),
                hipP,
                isValidHipP
            ) {
                viewModel.onFieldChange(weight, height, waistP, it)
            }
            cmicon()

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            SaveButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                isValidForm
            ) {
                viewModel.onUpdate(userViewModel.user)
                viewModel.clearData()
                viewModel.clearStatus()
            }
        }
    }

}


@Composable
fun SaveButton(
    modifier: Modifier,
    isValidForm: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        enabled = isValidForm,
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .width(330.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF034189)
        ),
    )
    {
        Text(
            text = "Guardar",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackPress : () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        // center items horizontally in the row
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { onBackPress() }
        ) {
            Icon(
                Icons.Outlined.ArrowBack,
                contentDescription = "Back button"
            )
        }
        Text(
            text = "Regresar",
            style = MaterialTheme.typography.titleSmall
        )
    }
}



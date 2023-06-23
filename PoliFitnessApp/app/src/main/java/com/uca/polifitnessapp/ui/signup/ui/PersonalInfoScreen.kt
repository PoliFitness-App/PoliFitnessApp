package com.uca.polifitnessapp.ui.signup.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.signup.validation.SignUpUiStatus
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpGoalViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@Composable
fun SignUpPersonalInfoScreen(
    navController: NavController,
    viewModel: SignUpGoalViewModel,
    userViewModel: UserViewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .verticalScroll(scrollState)
            .padding(50.dp, 0.dp, 30.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ---
        // Header Image
        //
        HeaderImage(modifier = Modifier.fillMaxWidth())

        // ---
        // Personal Info
        // ---
        SignUpPersonalInfoView(
            viewModel,
            navController
        )

        Spacer(modifier = Modifier.height(30.dp))

    }
}


@Composable
fun SignUpPersonalInfoView(
    viewModel: SignUpGoalViewModel,
    navController: NavController
) {

    // ---
    // Variables
    // ---

    val gender: String by viewModel.gender.observeAsState(initial = "")
    val birthdate: String by viewModel.dateOfBirth.observeAsState(initial = "")
    val weight: String by viewModel.weight.observeAsState(initial = "")
    val height: String by viewModel.height.observeAsState(initial = "")
    val waistP: String by viewModel.waistP.observeAsState(initial = "")
    val hipP: String by viewModel.hipP.observeAsState(initial = "")

    // ---
    // States
    // ---

    val isValidWeight: Boolean by viewModel.isValidWeight.observeAsState(initial = false)
    val isValidHeight: Boolean by viewModel.isValidHeight.observeAsState(initial = false)
    val isValidWaistP: Boolean by viewModel.isValidWaistP.observeAsState(initial = false)
    val isValidHipP: Boolean by viewModel.isValidHipP.observeAsState(initial = false)

    // Button
    val isEnabled: Boolean by viewModel.isSignUpButton2.observeAsState(initial = false)

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        // ---
        // GenderField
        // ---
        GenderField(
            gender
        ) {
            viewModel.onGenderChange(it)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // ---
        // BirthdateField
        // ---
        BirthdayField(
            birthdate
        ) {
            viewModel.onAgeChange(it)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // ---
        // WeightField
        // ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Top,
        ) {
            WeightField(
                modifier = Modifier.align(Alignment.CenterVertically),
                weight,
                isValidWeight
            ) {
                viewModel.onWeightChange(it)
            }
            kgicon(
                viewModel.weightUnitState
            ){
                viewModel.changeUnit()
            }
        }

        // ---
        // HeightField
        // ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Top,
        ) {
            HeightField(
                modifier = Modifier.align(Alignment.CenterVertically),
                height,
                isValidHeight
            ) {
                viewModel.onHeightChange(it)
            }
            cmicon()
        }

        // ---
        // WaistField
        // ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Top,
        ) {
            WaistField(
                modifier = Modifier.align(Alignment.CenterVertically),
                waistP,
                isValidWaistP
            ) {
                viewModel.onWaistPChange(it)
            }
            cmicon()
        }

        // ---
        // HipField
        // ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Top,
        ) {

            hipField(
                modifier = Modifier.align(Alignment.CenterVertically),
                hipP,
                isValidHipP
            ) {
                viewModel.onHipPChange(it)
            }

            cmicon()

        }

        Spacer(modifier = Modifier.height(15.dp))
        // ---
        // Button
        // ---
        SaveButton(
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            ),
            isEnabled
        ) {
            coroutineScope.launch {
                navController.navigate(AuthRoutes.GOAL_SCREEN)
            }
        }
    }
}


// -----------

// HEADER IMAGE

// ------------


@Composable
fun HeaderImage(
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .size(200.dp)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightField(
    modifier: Modifier,
    weight: String,
    isValidWeight: Boolean,
    onWeightChange: (String) -> Unit
) {
    TextField(
        value = weight,
        onValueChange = { onWeightChange(it) },
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
        isError = isValidWeight,
        trailingIcon = {
            if (isValidWeight) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error Icon"
                )
            }
        },
        supportingText = {
            if (isValidWeight) {
                Text(
                    text = "Formato incorrecto (Ej: 70.5 kg)",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(240.dp),
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
fun HeightField(
    modifier: Modifier,
    height: String,
    isWrongHeight: Boolean,
    onHeightChange: (String) -> Unit
) {
    TextField(
        value = height,
        onValueChange = {
            onHeightChange(it)
        },
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
        isError = isWrongHeight,
        trailingIcon = {
            if (isWrongHeight) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error Icon"
                )
            }
        },
        supportingText = {
            if (isWrongHeight) {
                Text(
                    text = "Formato incorrecto (ej: 1.70 m)",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(240.dp),
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
fun WaistField(
    modifier: Modifier,
    waist: String,
    isValidWaist: Boolean,
    onWaistChange: (String) -> Unit
) {
    TextField(
        value = waist,
        onValueChange = {
            onWaistChange(it)
        },
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
                text = "Perímetro de cintura",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        isError = isValidWaist,
        trailingIcon = {
            if (isValidWaist) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error Icon"
                )
            }
        },
        supportingText = {
            if (isValidWaist) {
                Text(
                    text = "Formato incorrecto (Ej: 70.5 cm)",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(240.dp),
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
    hip: String,
    isValidHip: Boolean,
    onHipChange: (String) -> Unit
) {
    TextField(
        value = hip,
        onValueChange = {
            onHipChange(it)
        },
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
                text = "Perímetro de cadera",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        isError = isValidHip,
        trailingIcon = {
            if (isValidHip) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error Icon"
                )
            }
        },
        supportingText = {
            if (isValidHip) {
                Text(
                    text = "Formato incorrecto (Ej: 70.5 cm)",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
            .width(240.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)
        )
    )
}

@Composable
fun kgicon(
    unit: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .height(56.dp)
            .width(48.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF034189))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = unit,
                color = Color.White
            )
        }

    }
}

@Composable
fun cmicon() {
    ElevatedCard(
        modifier = Modifier
            .height(56.dp)
            .width(48.dp)
        ,
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


@Composable
fun SaveButton(
    modifier: Modifier,
    isEnabled: Boolean,
    onSignupSelected: () -> Unit
) {
    Button(
        onClick = { onSignupSelected() },
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderField(
    gender: String,
    onGenderChange: (String) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        }
    ) {
        TextField(
            value = gender,
            onValueChange = {
                onGenderChange(it)
            },
            shape = MaterialTheme.shapes.small,
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Male,
                    contentDescription = "null",
                    tint = Color(0xFF565E71)
                )
            },
            label = {
                Text(
                    text = "Escoge tu genero",
                    color = Color(0xFF565E71),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF565E71),
                unfocusedBorderColor = Color.Transparent,
            ),
            modifier = Modifier
                .menuAnchor()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFD7E2FF))
                .width(300.dp)
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            DropdownMenuItem(
                text = { Text(text = "Femenino") },
                onClick = {
                    onGenderChange("Femenino")
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Masculino") },
                onClick = {
                    onGenderChange("Masculino")
                    isExpanded = false
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayField(
    dateCalendar: String,
    onDateChange: (String) -> Unit
) {

    val openDialog = remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(dateCalendar) }
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        date =
                            SimpleDateFormat("yyyy/dd/MM").format(datePickerState.selectedDateMillis)
                                .toString()
                        onDateChange(date)
                    },
                ) {
                    Text(
                        text = "Aceptar",
                        color = Color(0xFF034189)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color(0xFF034189)
                    )
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = Color(0xFF034189),
                    todayDateBorderColor = Color(0xFF034189),
                )
            )
        }
    }
    LaunchedEffect(dateCalendar) {
        onDateChange(dateCalendar)
    }
    Box(
        modifier = Modifier
            .width(300.dp)
            .clickable {
                openDialog.value = true
            },
    ) {
        TextField(
            value = dateCalendar,
            onValueChange = {
                onDateChange(dateCalendar)
            },
            shape = MaterialTheme.shapes.small,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "null",
                    tint = Color(0xFF565E71)
                )
            },
            label = {
                Text(
                    text = "Fecha de nacimiento",
                    color = Color(0xFF565E71),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .pointerInput(Unit) {}
                .width(300.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { openDialog.value = true },
            readOnly = true,
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF565E71),
                unfocusedBorderColor = Color.Transparent,
                containerColor = Color(0xFFD7E2FF)

            )
        )
    }


}




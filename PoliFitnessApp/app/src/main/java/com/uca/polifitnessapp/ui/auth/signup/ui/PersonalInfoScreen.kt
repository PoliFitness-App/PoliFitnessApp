@file:OptIn(ExperimentalMaterial3Api::class)

package com.uca.polifitnessapp.ui.auth.signup.ui

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.auth.signup.validation.SignUpUiStatus
import com.uca.polifitnessapp.ui.auth.signup.viewmodel.SignUpGoalViewModel
import com.uca.polifitnessapp.ui.auth.signup.viewmodel.TextFieldState
import com.uca.polifitnessapp.ui.extras.ui.TextFieldWithIcon
import com.uca.polifitnessapp.ui.main.calculator.ui.ValueState
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@Composable
fun SignUpPersonalInfoScreen(
    viewModel: SignUpGoalViewModel,
    onSignUpSuccess: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .verticalScroll(scrollState)
            .padding(30.dp, 0.dp, 30.dp, 16.dp),
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
            onSignUpSuccess
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}


@Composable
fun SignUpPersonalInfoView(
    viewModel: SignUpGoalViewModel,
    onSignUpSuccess: () -> Unit,
) {
    // Button
    val isEnabled: Boolean by viewModel.isSignUpButton2.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ---
        // GenderField
        // ---
        GenderField(
            viewModel.gender.value
        ) {
            viewModel.onGenderChange(it)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // ---
        // BirthdateField
        // ---
        BirthdayField(
            viewModel.dateOfBirth.value
        ) {
            viewModel.onAgeChange(it)
        }

        Spacer(modifier = Modifier.height(15.dp))

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
            isEnabled
        ) {
            onSignUpSuccess()
        }
    }
}

/*
* Text field item
 */

@Composable
fun TextFieldIcon(
    modifier: Modifier,
    icon: ImageVector,
    state: TextFieldState,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    iconText: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    onIconClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.Top,
    ) {
        TextField(
            value = state.value,
            isError = state.error.isNotEmpty(),
            onValueChange = {
                onValueChange(it)
            },
            shape = MaterialTheme.shapes.small,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "null",
                    tint = Color(0xFF565E71)
                )
            },
            label = {
                Text(
                    text = labelText,
                    color = Color(0xFF565E71),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            trailingIcon = {
                if (state.error.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = "Error Icon"
                    )
                }
            },
            supportingText = {
                if (state.error.isNotEmpty()) {
                    Text(
                        text = state.error,
                        color = Color.Red,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            },
            modifier = modifier
                .width(240.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF565E71),
                unfocusedBorderColor = Color.Transparent,
                containerColor = Color(0xFFD7E2FF)
            )
        )
        ElevatedCard(
            modifier = Modifier
                .height(56.dp)
                .width(48.dp)
                .clickable {
                    onIconClick()
                },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF034189))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = iconText,
                    color = Color.White
                )
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
                            SimpleDateFormat("yyyy/MM/dd").format(datePickerState.selectedDateMillis)
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




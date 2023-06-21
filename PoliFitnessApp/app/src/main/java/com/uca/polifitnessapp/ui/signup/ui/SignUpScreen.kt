package com.uca.polifitnessapp.ui.signup.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpGoalViewModel
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpGoalViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .verticalScroll(rememberScrollState())
            .padding(25.dp, 0.dp, 25.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignUpView(
            Modifier.align(
                Alignment.CenterHorizontally,
            ),
            viewModel,
            navController
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun SignUpView(
    modifier: Modifier,
    viewModel: SignUpGoalViewModel,
    navController: NavController
) {

    // --
    // View model variables
    // --

    val username: String by viewModel.username.observeAsState(initial = "")
    val lastname: String by viewModel.lastname.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")

    val checkbox: Boolean by viewModel.checkBox.observeAsState(initial = false)

    val coroutineScope = rememberCoroutineScope()

    val isWrongInputName: Boolean by viewModel.isValidUsername.observeAsState(initial = false)
    val isWrongInputLastName: Boolean by viewModel.isValidLastname.observeAsState(initial = false)
    val isWrongInputEmail: Boolean by viewModel.isValidEmail.observeAsState(initial = false)
    val isWrongInputPassword: Boolean by viewModel.isValidPassword.observeAsState(initial = false)

    val signUpEnable: Boolean by viewModel.isSignUpButton1.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        SignUpHeaderText()

        Spacer(modifier = Modifier.height(10.dp))

        NameField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            username,
            isWrongInputName,
        ) {
            viewModel.onUsernameChange(it)
        }


        LastNameField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            lastname,
            isWrongInputLastName,
        ) {
            viewModel.onLastnameChange(it)
        }


        EmailField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            email,
            isWrongInputEmail
        ) {
            viewModel.onEmailChange(it)
        }


        PasswordField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            password,
            isWrongInputPassword
        ) {
            viewModel.onPasswordChange(it)
        }

        TermsAndConditionText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            checkbox,
        ) {
            viewModel.onCheckboxChanged(it)
        }

        SignUpButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            signUpEnable
        ) {
            coroutineScope.launch {
                // We set the dat  -> name, lastname, email, password
                navController.navigate(AuthRoutes.PERSONAL_INFO_SCREEN)
            }
        }

    }

}


// -----------

// HEADER TEXT

// ------------

@Composable
fun SignUpHeaderText(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hey!")


        Text(
            text = "Crea una cuenta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )


    }
}


// NAME FIELD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(
    modifier: Modifier,
    name: String,
    isWrongInput: Boolean,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = name,
        onValueChange = {
            onTextFieldChanged(it)
        },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Nombre",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        isError = isWrongInput,
        supportingText = {
            if (isWrongInput) {
                Text(
                    text = "Ingresa un nombre válido",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        trailingIcon = {
            if (isWrongInput) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error Icon"
                )
            }
        },
        modifier = modifier
            .width(315.dp),
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
fun LastNameField(
    modifier: Modifier,
    lastname: String,
    isWrongInput: Boolean,
    onTextFieldChanged: (String) -> Unit

) {

    TextField(
        value = lastname,
        onValueChange = { onTextFieldChanged(it) },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Apellido",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        isError = isWrongInput,
        supportingText = {
            if (isWrongInput) {
                Text(
                    text = "Ingresa un apellido válido",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        trailingIcon = {
            if (isWrongInput) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error Icon"
                )
            }
        },

        modifier = modifier
            .width(315.dp),
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
fun EmailField(
    modifier: Modifier,
    email: String,
    isWrongInput: Boolean,
    onTextFieldChanged: (String) -> Unit
) {

    TextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.MailOutline,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Correo electrónico",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = isWrongInput,
        supportingText = {
            if (isWrongInput) {
                Text(
                    text = "Formato incorrecto (example@gmail.com)",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        trailingIcon = {
            if (isWrongInput) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error Icon"
                )
            }
        },

        modifier = modifier
            .width(315.dp),
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
fun PasswordField(
    modifier: Modifier,
    password: String,
    isWrongInput: Boolean,
    onTextSelected: (String) -> Unit
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    TextField(
        value = password,
        onValueChange = {
            onTextSelected(it)
        },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Contraseña",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = isWrongInput,
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }
            var description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        supportingText = {
            if (isWrongInput) {
                Text(
                    text = "La contraseña debe de tener entre 8 y 32 chars, y al menos 1 M, 1 m y 1 #",
                    color = Color.Red,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else
            PasswordVisualTransformation(),

        modifier = modifier
            .width(315.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        ),


        )

}


@Composable
fun SignUpButton(
    modifier: Modifier,
    signupEnabled: Boolean,
    onSignupSelected: () -> Unit
) {
    Button(
        onClick = {
            onSignupSelected()
        },
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .width(315.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF034189)
        ),
        enabled = signupEnabled,
    )
    {
        Text(
            text = "Crear",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TermsAndConditionText(
    modifier: Modifier,
    checkbox: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .padding(0.dp, 5.dp, 0.dp, 5.dp)
            .width(320.dp)
    ) {
        Checkbox(
            checked = checkbox,
            onCheckedChange = {
                onCheckedChanged(it)
            },
            colors = CheckboxDefaults.colors(Color.DarkGray),
        )
        Text(
            text = "Si continúa navegando, consideramos que acepta nuestra Politica de Privacidad y Terminos de Uso.",
            fontSize = 12.sp,
            color = Color(0xFF565E71),
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun GoogleLogin(
    modifier: Modifier,
    navController: NavController
) {
    Image(
        painter = painterResource(id = R.drawable.or),
        contentDescription = "Google Login",
        modifier = modifier
            .width(315.dp)
            .height(18.dp)
    )

    Spacer(modifier = Modifier.padding(2.dp))

    Image(
        painter = painterResource(id = R.drawable.google),
        contentDescription = "Google Login",
        modifier = modifier
            .padding(4.dp)
            .width(50.dp)
            .height(50.dp)
            .clickable { /*TODO*/ }
    )

    Row(modifier = modifier.padding(2.dp)) {
        Text(
            text = "¿Ya tienes una cuenta? ",
            fontSize = 14.sp
        )
        Text(
            text = "Login",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF034189),
            modifier = modifier.clickable {
                // Navigate lo Log In
                navController.navigate(AuthRoutes.LOGIN_SCREEN)
            }
        )
    }
}


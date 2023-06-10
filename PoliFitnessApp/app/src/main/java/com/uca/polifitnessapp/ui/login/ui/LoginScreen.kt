@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LoginView(
            Modifier.align(Alignment.Center),
            viewModel,
            navController
        ) // Para aliniar to do al centro
    }

}

@Composable
fun LoadingView() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
fun LoginView(modifier: Modifier, viewModel: LoginViewModel, navController: NavHostController) {

    // CREAR NUESTRAS VARIABLES DE ESTADO PARA EL EMAIL Y PASSWORD

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)

    val coroutineScope = rememberCoroutineScope()

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val isWrongInput: Boolean by viewModel.isWrongInput.observeAsState(initial = false)

    if (isLoading) {
        LoadingView()
    } else {
        Column(modifier = modifier) {

            // HEADER

            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))

            // LOGIN FORM

            EmailField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                email,
                isWrongInput
            ) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(8.dp))

            PasswordField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                password,
                isWrongInput
            ) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))

            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(8.dp))

            LoginButton(
                Modifier.align(Alignment.CenterHorizontally),
                loginEnable
            ) {
                coroutineScope.launch {
                    navController.navigate("main_flow")
                    viewModel.onLoginSelected()
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            // GOOGLE LOGIN

            GoogleLogin(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }
    }
}


// -----------

// HEADER IMAGE

// ------------


@Composable
fun HeaderImage(modifier: Modifier) {
    Text(
        text = "Hey!",
        fontSize = 16.sp,
        modifier = modifier.padding(bottom = 8.dp)
    )
    Text(
        text = "Bienvenido de nuevo",
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        modifier = modifier.padding(bottom = 16.dp)
    )
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Header",
        modifier = modifier
            .width(190.dp)
            .height(220.dp),
        contentScale = androidx.compose.ui.layout.ContentScale.Fit
    )
}

// -----------

// EMAIL FIELD

// ------------

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

// -----------

// PASSWORD FIELD

// ------------

@Composable
fun PasswordField(
    modifier: Modifier,
    passwordViewModel: String,
    isWrongInput: Boolean,
    onTextFieldChanged: (String) -> Unit
) {

    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    TextField(
        value = passwordViewModel,
        onValueChange = { onTextFieldChanged(it) },
        singleLine = true,
        label = {
            Text(
                text = "Contraseña",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "LockIcon",
                tint = Color(0xFF565E71),
            )
        },

        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                // Please provide localized description for accessibility services
                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(
                    imageVector = visibilityIcon,
                    contentDescription = description)
            }
        },


        modifier = modifier
            .width(315.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)
        )
    )
}

// -----------

//  FORGOT PASSWORD

// ------------

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "¿Ha olvidado su contraseña?",
        modifier = modifier.clickable { /*TODO*/ },
        fontSize = 12.sp,
        color = Color(0xFF565E71),
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline
    )
}

// -----------

// LOGIN BUTTON

// ------------

@Composable
fun LoginButton(
    modifier: Modifier,
    loginEnable: Boolean,
    onLoginSelected: () -> Unit
) {
    Button(
        onClick = {
            onLoginSelected()
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
        enabled = loginEnable
    )
    {
        Text(
            text = "Iniciar sesión",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

// -----------

// GOOGLE LOGIN BUTTON

// ------------

@Composable
fun GoogleLogin(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.or),
        contentDescription = "Google Login",
        modifier = modifier
            .width(315.dp)
            .height(18.dp)
    )

    Spacer(modifier = Modifier.padding(8.dp))

    Image(
        painter = painterResource(id = R.drawable.google),
        contentDescription = "Google Login",
        modifier = modifier
            .padding(4.dp)
            .width(50.dp)
            .height(50.dp)
            .clickable { /*TODO*/ }
    )

    Spacer(modifier = Modifier.padding(8.dp))

    Row(modifier = modifier.padding(4.dp)) {
        Text(
            text = "¿Aún no tienes una cuenta? ",
            fontSize = 14.sp
        )
        Text(
            text = "Registrarse",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF034189),
            modifier = modifier.clickable { /*TODO*/ }
        )
    }
}











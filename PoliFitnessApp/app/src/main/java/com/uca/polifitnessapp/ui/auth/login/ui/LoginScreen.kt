@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.auth.login.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.auth.login.state.LoginUiStatus
import com.uca.polifitnessapp.ui.auth.login.state.UserState
import com.uca.polifitnessapp.ui.auth.login.viewmodel.LoginViewModel
import com.uca.polifitnessapp.ui.navigation.components.LoadingScreen
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.navigation.flows.MainRoutes
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    userViewModel: UserViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginView(
            Modifier.align(Alignment.CenterHorizontally),
            viewModel,
            userViewModel,
            onLoginSuccess,
            onNavigateToSignUp
        )
    }

}
@Composable
fun LoginView(
    modifier: Modifier,
    viewModel: LoginViewModel,
    userViewModel: UserViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {

    // State variables
    // email and password
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")

    // Is login enable? (email and password are not empty)
    val loginEnable: Boolean by viewModel.isLoginEnable.observeAsState(initial = false)

    // Is wrong email? (email is not empty but is not a valid email)
    val isWrongEmail: Boolean by viewModel.isValidEmail.observeAsState(initial = false)

    // Is wrong password? (password is not empty but is not a valid password)
    val isWrongPassword: Boolean by viewModel.isValidPassword.observeAsState(initial = false)

    val isLoginLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    // Application Instance
    val app: PoliFitnessApplication = LocalContext.current.applicationContext as PoliFitnessApplication
    // Context and LifecycleOwner
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Handle status changes
    
    fun handleUiStatus(status: LoginUiStatus) {

        when(status) {
            is LoginUiStatus.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()

            }
            is LoginUiStatus.ErrorWithMessage -> {
                Toast.makeText(context, status.message, Toast.LENGTH_SHORT).show()
            }
            is LoginUiStatus.Success -> {
                // login view model action -> clear status, and clear data
                viewModel.clearStatus()
                viewModel.clearData()
                // then we save the token
                app.saveAuthToken(token = status.token)
                // and we set the user state
                app.saveUserState(UserState.LOGGED_IN)
                // information about the state
                Toast.makeText(context, "Inicio de sesion correcto", Toast.LENGTH_SHORT).show()

                // we call the method viewmodel.getUset to get the information about the user
                // using the repository.whoami that returns that information

                userViewModel.getUser()
                onLoginSuccess()
            }
            else -> {}
        }
    }

    val coroutineScope = rememberCoroutineScope()

    if (isLoginLoading){
        LoadingScreen()
    } else {
        Column(modifier = modifier) {

            // ---
            // Header
            // ---

            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))

            // ---
            // Login form
            // ---

            EmailField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                email,
                isWrongEmail
            ) {
                viewModel.onLoginChanged(it, password)
            }
            Spacer(modifier = Modifier.padding(8.dp))

            PasswordField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                password,
                isWrongPassword
            ) {
                viewModel.onLoginChanged(email, it)
            }
            Spacer(modifier = Modifier.padding(8.dp))

            /*
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(8.dp))
             */

            LoginButton(
                Modifier.align(Alignment.CenterHorizontally),
                loginEnable
            ) {
                coroutineScope.launch {
                    viewModel.status.observe(lifecycleOwner) { status ->
                        handleUiStatus(status)
                    }
                    viewModel.onLogin()
                    viewModel.token.value = app.getToken()
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            // Sign un option
            SignUpOption(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onNavigateToSignUp
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
        fontSize = 14.sp,
        modifier = modifier.padding(bottom = 8.dp)
    )
    Text(
        text = "Bienvenido de nuevo",
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        modifier = modifier.padding(bottom = 16.dp)
    )
    Image(
        painter = painterResource(id = R.drawable.image_login_screen),
        contentDescription = "Header",
        modifier = modifier
            .width(190.dp)
            .height(220.dp)
    )
}

// -----------

// EMAIL FIELD

// ------------

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
                    contentDescription = description
                )
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
fun SignUpOption(
    modifier: Modifier,
    onNavigateToSignUp: () -> Unit
) {
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
            modifier = modifier.clickable {
                onNavigateToSignUp()
            }
        )
    }

    Spacer(
        modifier =
        Modifier.padding(10.dp)
    )
}











package com.uca.polifitnessapp.ui.signup.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.signup.ui.validation.UiEvent
import com.uca.polifitnessapp.ui.signup.ui.viewmodel.SignUpViewModel




@Composable
fun SignUpScreen(viewModel: SignUpViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpHeaderText()
        combine(viewModel)
        GoogleLogin(modifier = Modifier.align(Alignment.CenterHorizontally))


    }
}




// -----------

// HEADER TEXT

// ------------

@Composable
fun SignUpHeaderText(
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hey!")


        Text(text = "Crea una cuenta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(
    modifier: Modifier,
    errorStatus: Boolean,
    onTextSelected: (String) -> Unit
){
    val textSate = remember { mutableStateOf("") }

    TextField(
        value = textSate.value,
        onValueChange = {
            textSate.value = it
            onTextSelected(it)
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
        modifier = modifier
            .width(315.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        ),
        isError = !errorStatus
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastNameField(
    modifier: Modifier,
    errorStatus: Boolean,
    onTextSelected: (String) -> Unit

){
    val textSate = remember { mutableStateOf("") }

    TextField(value = textSate.value,
        onValueChange = {
            textSate.value = it
            onTextSelected(it)   },
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = modifier
            .width(315.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        ),
        isError = !errorStatus
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    modifier: Modifier,
    errorStatus: Boolean,
    onTextSelected: (String) -> Unit
){
    val textSate = remember { mutableStateOf("") }

    TextField(value = textSate.value,
        onValueChange = {
            textSate.value = it
            onTextSelected(it)},

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
        modifier = modifier
            .width(315.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        ),
        isError = !errorStatus
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    modifier: Modifier,
    errorStatus: Boolean,
    onTextSelected: (String) -> Unit
){
    val password = remember { mutableStateOf("") }

    val passwordVisible = remember{
        mutableStateOf(false)
    }

    TextField(value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it) },
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
        trailingIcon = {
            val iconImage = if (passwordVisible.value){
                Icons.Outlined.Visibility
            }else {
                Icons.Outlined.VisibilityOff
            }
            var description = if(passwordVisible.value){
                "Hide password"
            }else{
                "Show password"
            }

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                Icon(imageVector = iconImage, contentDescription = description)

            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else
            PasswordVisualTransformation(),

        modifier = modifier
            .width(315.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        ),
        isError = !errorStatus

    )

}




@Composable()
fun combine( signUpViewModel: SignUpViewModel){


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            NameField(
                modifier = Modifier.align(Alignment.CenterVertically),
                errorStatus = signUpViewModel.signUpUiState.value.nameError,
                onTextSelected = {signUpViewModel.onEvent(UiEvent.NameChanged(it))}
            )


        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            LastNameField(
                modifier = Modifier.align(Alignment.CenterVertically),
                errorStatus = signUpViewModel.signUpUiState.value.lastnameError,
                onTextSelected = {signUpViewModel.onEvent(UiEvent.Lastnamehanged(it))}

            )


        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            EmailField(
                modifier = Modifier.align(Alignment.CenterVertically),
                errorStatus = signUpViewModel.signUpUiState.value.emailError,
                onTextSelected = {signUpViewModel.onEvent(UiEvent.EmailChanged(it))}

            )


        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            PasswordField(
                modifier = Modifier.align(Alignment.CenterVertically),
                errorStatus = signUpViewModel.signUpUiState.value.passwordError,
                onTextSelected = {signUpViewModel.onEvent(UiEvent.PasswordChanged(it))}
            )

        }


        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            TermsAndConditionText()
        }

        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            SignUpButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                signUpViewModel
            )

        }




    }

}


@Composable
fun SignUpButton(modifier: Modifier,
    signUpViewModel: SignUpViewModel
) {
    Button(
        onClick = { signUpViewModel.onEvent(UiEvent.SignUpButtonClicked)},
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
fun TermsAndConditionText() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember { mutableStateOf(false) }
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = !checkedState.value },
            colors = CheckboxDefaults.colors(Color.Black)
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
fun GoogleLogin(modifier: Modifier) {
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

    Spacer(modifier = Modifier.padding(2.dp))

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
            modifier = modifier.clickable { /*TODO*/ }
        )
    }
}





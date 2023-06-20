@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.contactscreen.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.ui.theme.md_theme_light_outline

@Composable
fun Contact(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.Start),
            navController
        )
        Spacer(modifier = Modifier.height(25.dp))

        ContactTitle()
        Spacer(modifier = Modifier.height(25.dp))

        contactDescription()
        Spacer(modifier = Modifier.height(25.dp))

        EmailField()
        Spacer(modifier = Modifier.height(50.dp))

        SendEmail()
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        // center items horizontally in the row
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                Icons.Outlined.ArrowBack,
                contentDescription = "Back button"
            )
        }
    }
}


@Composable
fun ContactTitle() {
    Text(
        modifier = Modifier
            .width(350.dp)
            .padding(35.dp, 0.dp),
        text = "¿Tienes alguna pregunta?",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun contactDescription() {
    Text(
        modifier = Modifier
            .width(350.dp)
            .padding(35.dp, 0.dp),
        text = "Introduzca su dirección de correo electrónico y nos pondremos en contacto con usted si tiene algún problema.",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}

@Composable
fun EmailField() {
    val textState = remember { mutableStateOf("") }

    TextField(
        value = textState.value,
        onValueChange = { newText ->
            textState.value = newText
        },
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
        modifier = Modifier
            .width(315.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)
        )
    )
}

@Composable
fun SendEmail() {
    Button(
        onClick = {

        },
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = Modifier
            .width(315.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF034189)
        ),
    )
    {
        Text(
            text = "Enviar",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

//TODO: Realizar el bottomSheet al enviar y averiguar como y donde enviaremos el email del usuario

@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.contactscreen.ui

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.theme.md_theme_dark_tertiary
import com.uca.polifitnessapp.ui.theme.md_theme_light_onPrimary
import com.uca.polifitnessapp.ui.theme.md_theme_light_outline
import com.uca.polifitnessapp.ui.theme.md_theme_light_primary
import com.uca.polifitnessapp.ui.theme.md_theme_light_scrim

@Composable
@Preview
fun Contact() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        Box(modifier = Modifier
            .fillMaxSize()) {

            Box(modifier = Modifier
                .align(Alignment.TopCenter)
            )
            {
                Column(modifier = Modifier) {
                    navigateBack()
                    Spacer(modifier = Modifier.height(25.dp))
                    contactTittle()
                    Spacer(modifier = Modifier.height(25.dp))
                    contactDescription()
                    Spacer(modifier = Modifier.height(25.dp))
                    emailFill()
                }
            }

            Box(modifier = Modifier
                .align(Alignment.BottomCenter)) {
                sendemail()
            }
        }
    }
}

@Composable
fun navigateBack() {

    Row(
        modifier = Modifier
            .padding(16.dp, 25.dp, 32.5.dp, 0.dp)
            .fillMaxWidth()
    ) {
        FloatingActionButton(
            onClick = { /* Acción al hacer clic en el FAB */ },
            modifier = Modifier,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            containerColor = Color.Transparent,
            content = {
                Icon(
                    painterResource(R.drawable.navigate_back_icon),
                    contentDescription = "Ver rutina",
                    tint = md_theme_light_scrim
                )
            }
        )
    }
}

@Composable
fun contactTittle() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "¿Tienes alguna pregunta?",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun contactDescription() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Introduzca su dirección de correo electrónico y nos pondremos en contacto con usted si tiene algún problema.",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}

@Composable
fun emailFill() {
    val textState = remember { mutableStateOf("") }

    Box(modifier = Modifier) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 5.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(color = md_theme_dark_tertiary)) {

            Spacer(modifier = Modifier.width(15.dp))

            Box(modifier = Modifier
                .padding(5.dp)
                .align(Alignment.CenterVertically)) {
                Icon(
                    painter = painterResource(R.drawable.outline_mail_24),
                    contentDescription = "Imagen de correo",
                    tint = md_theme_light_outline)
            }

            OutlinedTextField(modifier = Modifier,
                value = textState.value,
                onValueChange = { newText ->
                    textState.value = newText
                },

                placeholder = {
                    Text(text = "Correo electrónico",
                        color = md_theme_light_outline,
                        fontSize = 12.sp)
                },

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedTextColor = md_theme_light_outline,
                )
            )
        }
    }
}

@Composable
fun sendemail() {

    Button(
        onClick = { /* Acción al hacer clic en el FAB */ },
        modifier = Modifier
            .padding(35.dp, 30.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = md_theme_light_primary
        ),
        shape = RoundedCornerShape(14.dp),
        content = {
            Text(
                text = "Enviar",
                fontWeight = FontWeight.Bold ,
                color = md_theme_light_onPrimary,
                fontSize = 14.sp
            )
        }
    )
}

//TODO: Realizar el bottomSheet al enviar y averiguar como y donde enviaremos el email del usuario

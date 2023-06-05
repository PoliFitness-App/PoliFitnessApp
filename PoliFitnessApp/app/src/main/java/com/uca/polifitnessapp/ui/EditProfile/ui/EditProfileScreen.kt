package com.uca.polifitnessapp.ui.EditProfile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.uca.polifitnessapp.R

@Preview
@Composable
fun EditProfileScreen(){
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
        HeaderImage()
        EditProfileText()
        combine()


    }
}


// -----------

// HEADER IMAGE

// ------------


@Composable
fun HeaderImage(){
    Box(
        modifier = Modifier
            .size(250.dp)
    ){
        Image(painter = painterResource(id = R.drawable.editprofileimg),
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentDescription = "Imagen " )
    }

}

// -----------

// HEADER TEXT

// ------------

@Composable
fun EditProfileText(){
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
            Text(text = "¿Es hora de un cambio?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Image(painter = painterResource(id = R.drawable.fireimg),
                contentDescription = "Imagen fuego" )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun weightField(
    modifier: Modifier
){
    val textSate = remember { mutableStateOf("") }

    TextField(value = textSate.value,
        onValueChange = { textSate.value = it },
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
    modifier: Modifier
){
    val textSate = remember { mutableStateOf("") }

    TextField(value = textSate.value,
        onValueChange = { textSate.value = it },
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
    modifier: Modifier
){
    val textSate = remember { mutableStateOf("") }

    TextField(value = textSate.value,
        onValueChange = { textSate.value = it },
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
    modifier: Modifier
){
    val textSate = remember { mutableStateOf("") }

    TextField(value = textSate.value,
        onValueChange = { textSate.value = it },
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
fun kgicon(){
    ElevatedCard(
        modifier = Modifier
            .height(74.dp)
            .size(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),colors = CardDefaults.cardColors( containerColor = Color(0xFF034189))
    ){
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
fun cmicon(){
    ElevatedCard(
        modifier = Modifier
            .height(74.dp)
            .size(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors( containerColor = Color(0xFF034189))
    ){
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
fun combine(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            weightField(modifier = Modifier.align(Alignment.CenterVertically))
            kgicon()

        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            heightField(modifier = Modifier.align(Alignment.CenterVertically))
            cmicon()

        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            waistField(modifier = Modifier.align(Alignment.CenterVertically))
            cmicon()

        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            hipField(modifier = Modifier.align(Alignment.CenterVertically))
            cmicon()

        }


        Row( horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {

            SaveButton(modifier = Modifier.align(Alignment.CenterVertically))

        }


        
    }

}


@Composable
fun SaveButton(modifier: Modifier) {
    Button(
        onClick = { "/*TODO*/ "},
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
            text = "Guardar",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}









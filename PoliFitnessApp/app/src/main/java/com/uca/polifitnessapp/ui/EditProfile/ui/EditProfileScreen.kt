package com.uca.polifitnessapp.ui.EditProfile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        HeaderImage()
        EditProfileText()

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
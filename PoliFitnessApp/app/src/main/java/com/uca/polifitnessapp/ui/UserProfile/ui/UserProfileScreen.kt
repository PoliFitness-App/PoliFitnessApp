package com.uca.polifitnessapp.ui.UserProfile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.uca.polifitnessapp.R

@Preview
@Composable
fun ProfileScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        UserCard()
        generalInfoUser()
    }
}

@Composable
fun UserCard(
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(painter = painterResource(id = R.drawable.profilepic),
            modifier = Modifier.size(50.dp),
            contentDescription = stringResource(
                id = R.string.description,


                )
        )
        Column(){
            Text(text= "Pamela Gómez")

            Text(text = "Programa de perder grasa",
                fontSize = 10.sp,
                fontWeight = FontWeight.Light
            )


        }
        Button(
            colors = ButtonDefaults.buttonColors(Color("#2E5DA8".toColorInt())),
            shape = RoundedCornerShape(20.dp),
            onClick = { "/*TODO*/ "}) {

            Text(text = "Editar", fontSize = 12.sp)

        }

    }
}

@Composable
fun generalInfoUser(){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "180cm", color = Color("#2E5DA8".toColorInt()))

                Text(text = "Altura", textAlign = TextAlign.Center)
            }

        }

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "65 kg", color = Color("#2E5DA8".toColorInt()))
                Text(text = "Peso")
            }

        }

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "22 años", color = Color("#2E5DA8".toColorInt()))
                Text(text = "Edad")
            }

        }
    }

}
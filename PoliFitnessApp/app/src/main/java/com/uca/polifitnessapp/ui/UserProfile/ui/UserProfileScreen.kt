package com.uca.polifitnessapp.ui.UserProfile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.models.UserModel
import com.uca.polifitnessapp.ui.navigation.UserScreens


@Composable
fun ProfileScreen(navController: NavController, user: UserModel){

    Column(


        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        UserCard(navController, user)
        generalInfoUser(user)
        specificlInfoUser()
        contactCard()
    }
}

@Composable
fun UserCard( navController: NavController, user: UserModel) {
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
            Text(text= user.name)

            Text(text = "Programa de perder grasa",
                fontSize = 10.sp,
                fontWeight = FontWeight.Light
            )


        }
        Button(
            colors = ButtonDefaults.buttonColors(Color("#2E5DA8".toColorInt())),
            shape = RoundedCornerShape(20.dp),
            onClick = { navController.navigate(route = UserScreens.EditProfileScreen.route)}) {

            Text(text = "Editar", fontSize = 12.sp)

        }

    }
}

@Composable
fun generalInfoUser(user: UserModel){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = user.height.toString(), color = Color("#2E5DA8".toColorInt()))

                Text(text = "Altura", textAlign = TextAlign.Center)
            }

        }

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = user.weight.toString() +"kg", color = Color("#2E5DA8".toColorInt()))
                Text(text = "Peso")
            }

        }

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = user.age.toString()+"años", color = Color("#2E5DA8".toColorInt()))
                Text(text = "Edad")
            }

        }
    }

}

@Composable
fun specificlInfoUser(){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {

        ElevatedCard(
            modifier = Modifier
                .padding(20.dp)
                .size(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "20.1", color = Color("#2E5DA8".toColorInt()))
                Text(text = "Normal", color = Color("#2E5DA8".toColorInt()))
                Text(text = "IMC (Indice de masa corporal)",
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center)
            }

        }

        ElevatedCard(
            modifier = Modifier
                .size(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "19.3 ", color = Color("#2E5DA8".toColorInt()))
                Text(text = "Normal", color = Color("#2E5DA8".toColorInt()))
                Text(text = "ICC (Indice cindtura-cadera)",
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )

            }

        }


    }

}


@Composable
fun contactCard() {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(20.dp)

        ) {
            Text(text = "Otros", fontWeight = FontWeight.Bold, fontSize = 20.sp,)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.mail),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

                // gap between icon and text
                Spacer(modifier = Modifier.width(width = 10.dp))

                Text(
                    text = "Contáctanos",
                    fontSize = 10.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = null,

                    )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.admin_panel_settings),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

                // gap between icon and text
                Spacer(modifier = Modifier.width(width = 6.dp))

                Text(
                    text = "Política de privacidad",
                    fontSize = 10.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = null,

                    )
            }


        }

    }
}

package com.uca.polifitnessapp.ui.user.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.navigation.flows.MainRoutes
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    // navController
    navController: NavController,
    // user view model
    userViewModel: UserViewModel,
    userId:String,
) {

    // ---
    // Fetch user from the userId
    // ---

    LaunchedEffect(userId) {
        userViewModel.fetchUserById(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        // New item screen
        val user = userViewModel.user

        UserCard(
            user,
            navController
        )
        generalInfoUser(user)
        specificlInfoUser(user)
        ContactCard(
            navController
        )

        try {
        } catch (e: Exception) {
            navController.navigate(AuthRoutes.LOGIN_SCREEN) {
                popUpTo(AuthRoutes.LOGIN_SCREEN) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun UserCard(
    user: UserModel,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 20.dp, 8.dp, 8.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.profilepic),
            modifier = Modifier.size(50.dp),
            contentDescription = stringResource(
                id = R.string.description,
            )
        )
        Column() {
            Text(
                text = user.username,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.scrim,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 0.dp),
                fontSize = 14.sp,
            )

            Text(
                text = user.approach,
                fontWeight = FontWeight.ExtraLight,
                color = MaterialTheme.colorScheme.scrim,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
            )


        }
        Button(
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(92.dp)
                .height(33.dp),
            onClick = {
                navController.navigate(MainRoutes.MAIN_USER_EDIT)
            }
        ) {
            Text(
                text = "Editar",
                fontSize = 12.sp
            )

        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun generalInfoUser(user: UserModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        ElevatedCard(
            modifier = Modifier
                .padding(8.dp)
                .width(105.dp)
                .height(73.dp),
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = user.height.toString() + " cm",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 14.sp,
                )

                Text(
                    text = "Altura",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .width(105.dp)
                .height(73.dp),
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = user.weight.toString() + " kg",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 14.sp,
                )
                Text(
                    text = "Peso",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val datOfBirth = LocalDate.parse(user.birthday, formatter)

        val age = Period.between(datOfBirth, LocalDate.now()).years

        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .width(105.dp)
                .height(73.dp),
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$age años",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 14.sp,
                )
                Text(
                    text = "Edad",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }

}


@Composable
fun specificlInfoUser(
    user: UserModel
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {

        ElevatedCard(
            modifier = Modifier
                .padding(20.dp)
                .width(160.dp)
                .height(120.dp),
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = user.imc.toString(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 16.sp,
                )

                //
                Text(
                    text = "Normal",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    fontSize = 14.sp,
                )

                //
                Text(
                    text = "IMC (Indice de masa corporal)",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

        ElevatedCard(
            modifier = Modifier
                .padding(20.dp)
                .width(160.dp)
                .height(120.dp),
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = user.icc.toString(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 16.sp,
                )

                //
                Text(
                    text = "Normal",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    fontSize = 14.sp,
                )

                //
                Text(
                    text = "ICC (Indice cintura - cadera)",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )

            }

        }


    }

}


@Composable
fun ContactCard(
    navController: NavController
) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .width(350.dp)
            .height(156.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)

        ) {
            Text(
                text = "Otros",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(MainRoutes.MAIN_CONTACT_INFO)
                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.mail),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

                Text(
                    text = "Contáctanos",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,

                    )

                // gap between icon and text
                Spacer(modifier = Modifier.width(width = 125.dp))

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = null,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(MainRoutes.MAIN_TERMS_AND_CONDITIONS)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.admin_panel_settings),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

                // gap between icon and text

                Text(
                    text = "Política de privacidad",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.width(width = 80.dp))

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = null,
                )
            }
        }
    }
}
package com.uca.polifitnessapp.ui.user.ui

import android.os.Build
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.auth.login.state.UserState
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.navigation.flows.MainRoutes
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

/**
 * @Composable User Profile Screen
 * @Description: This composable show the user profile screen
 *
 * @param: userViewModel: UserViewModel (User view model)
 * @param: user: UserModel (User info)
 * @param: onNavigateToEditProfile: (String) -> Unit (Navigation to edit profile screen)
 * @param: onNavigateToTermsAndConditions: () -> Unit (Navigation to terms and conditions screen)
 * @param: onNavigateToContactUs: () -> Unit (Navigation to contact us screen)
 **/

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    userId: String,
    onNavigateToEditProfile: (String) -> Unit,
    onNavigateToTermsAndConditions: () -> Unit,
    onNavigateToContactUs: () -> Unit,
    onLogout: () -> Unit
) {
    // Fetch user from the userId
    LaunchedEffect(userId) {
        userViewModel.getUserInfo()
        userViewModel.fetchUserById(userId)
    }

    // Scroll state to scroll the screen, when the screen is too long
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(30.dp, 30.dp, 30.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {

        // New item screen
        val user = userViewModel.user

        UserCard(
            user,
            onNavigateToEditProfile
        )
        GeneralInfoUser(user)
        UserInfo(user)
        ContactCard(
            onNavigateToTermsAndConditions,
            onNavigateToContactUs,
            onLogout
        )

        Spacer(modifier = Modifier.height(60.dp))
    }
}

/**
 * @Composable User Card
 * @Description: This composable show the user card (profile pic, username, approach, edit button)
 *
 * @param: user: UserModel (User info)
 * @param: onNavigateToEditProfile: (String) -> Unit (Navigation to edit profile screen)
 **/

@Composable
fun UserCard(
    user: UserModel,
    onNavigateToEditProfile: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.profilepic),
            modifier = Modifier.size(50.dp),
            contentDescription = stringResource(
                id = R.string.description,
            )
        )

        Spacer(modifier = Modifier.width(20.dp))

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

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = user.approach,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.scrim,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
            )

        }
        Spacer(modifier = Modifier.width(20.dp))

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                onNavigateToEditProfile(user._id)
            },
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            modifier = Modifier
                .width(100.dp)
                .height(40.dp)
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
        ) {
            Text(
                text = stringResource(R.string.button_edit_title),
                fontSize = 12.sp
            )
        }
    }
}

/**
 * @Composable General info user
 * @Description: This composable show the general user info (height, weight, age,...)
 *
 * @param: user: UserModel (User info)
 **/

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeneralInfoUser(
    user: UserModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ElevatedCard(
            modifier = Modifier
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
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

        Spacer(modifier = Modifier.width(10.dp))

        ElevatedCard(
            modifier = Modifier
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
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

        Spacer(modifier = Modifier.width(10.dp))

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val datOfBirth = LocalDate.parse(user.birthday, formatter)

        val age = Period.between(datOfBirth, LocalDate.now()).years

        ElevatedCard(
            modifier = Modifier
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
                    fontWeight = FontWeight.Light,
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

/**
 * @Composable User Info
 * @Description: This composable show the user info
 *
 * @param: user: UserModel (User info)
 **/

@Composable
fun UserInfo(
    user: UserModel
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ElevatedCard(
            modifier = Modifier
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
                    text =
                        when {
                            user.imc < 21 -> stringResource(R.string.under_weight_imc)
                            user.imc in 21.0..32.9 -> stringResource(R.string.normal_weight_imc)
                            user.imc in 33.0..38.9 -> stringResource(R.string.high_weight_imc)
                            user.imc >= 39.0 -> stringResource(R.string.warning_weight_imc)
                            else -> stringResource(R.string.error_imc)
                        },
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    fontSize = 14.sp,
                )

                //
                Text(
                    text = stringResource(R.string.imc_title),
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

        Spacer(modifier = Modifier.width(10.dp))

        ElevatedCard(
            modifier = Modifier
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
                    text = if (user.gender) {
                        when {
                            user.icc < 0.95 -> stringResource(R.string.no_risk_icc)
                            user.icc in 0.96 .. 0.99 -> stringResource(R.string.risk_icc)
                            user.icc > 0.99 -> stringResource(R.string.high_risk_icc)
                            else -> stringResource(R.string.error_icc)
                        }
                    } else {
                        when {
                            user.icc < 0.80 -> stringResource(R.string.no_risk_icc)
                            user.icc in 0.81 .. 0.84 -> stringResource(R.string.risk_icc)
                            user.icc > 0.85 -> stringResource(R.string.high_risk_icc)
                            else -> stringResource(R.string.error_icc)
                        }
                    },
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    fontSize = 14.sp,
                )

                //
                Text(
                    text = stringResource(R.string.icc_title),
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                )
            }
        }
    }
}

/**
 * @Composable ContactCard
 * @Description: This is the contact card that contains the terms and conditions, contact us, and logout button
 *
 * @param: onNavigateToTermsAndConditions: () -> Unit, (To navigate to terms and conditions)
 * @param: onNavigateToContactUs: () -> Unit, (To navigate to contact us)
 **/
@Composable
fun ContactCard(
    onNavigateToTermsAndConditions: () -> Unit,
    onNavigateToContactUs: () -> Unit,
    onLogout: () -> Unit
) {
    // Application Instance
    val app: PoliFitnessApplication =
        LocalContext.current.applicationContext as PoliFitnessApplication
    val context = LocalContext.current

    ElevatedCard(
        modifier = Modifier
            .width(350.dp)
            .height(200.dp),
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
                text = stringResource(R.string.other_title),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onNavigateToContactUs()
                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.mail),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

                Text(
                    text = stringResource(R.string.contact_us_title),
                    fontWeight = FontWeight.Light,
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
                        onNavigateToTermsAndConditions()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.admin_panel_settings),
                    contentDescription = stringResource(R.string.arrow_icon_title),
                    modifier = Modifier.size(28.dp)
                )

                // gap between icon and text

                Text(
                    text = stringResource(R.string.privacy_policy_title),
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.width(width = 80.dp))

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = stringResource(R.string.arrow_icon_title),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onLogout()
                        // Logout
                        // We update the user state
                        app.saveUserState(UserState.LOGGED_OUT)
                        // We update the token
                        app.saveAuthToken(token = "")
                        // Then
                        // information about the state
                        Toast
                            .makeText(context, "Ha cerrado su sesión", Toast.LENGTH_SHORT)
                            .show()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logout_2),
                    contentDescription = stringResource(R.string.arrow_icon_title),
                    modifier = Modifier.size(28.dp)
                )

                // gap between icon and text

                Text(
                    text = stringResource(R.string.logout_title),
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.width(width = 80.dp))

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = stringResource(R.string.arrow_icon_title)
                )
            }
        }
    }
}
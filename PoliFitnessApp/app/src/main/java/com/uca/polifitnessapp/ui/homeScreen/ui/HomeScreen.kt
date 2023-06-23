@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.homeScreen.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel
import com.uca.polifitnessapp.ui.homeScreen.state.HomeUiStatus
import com.uca.polifitnessapp.ui.homeScreen.viewmodel.HomeScreenViewModel
import com.uca.polifitnessapp.ui.navigation.components.LoadingScreen
import com.uca.polifitnessapp.ui.news.ui.NewItem
import com.uca.polifitnessapp.ui.theme.md_theme_light_onPrimary
import com.uca.polifitnessapp.ui.theme.md_theme_light_outline
import com.uca.polifitnessapp.ui.theme.md_theme_light_primary
import com.uca.polifitnessapp.ui.theme.md_theme_light_scrim
import com.uca.polifitnessapp.ui.theme.md_theme_light_secondary
import com.uca.polifitnessapp.ui.theme.md_theme_light_secondaryContainer
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel

@Composable
fun Home(
    homeScreenViewModel: HomeScreenViewModel,
    userViewModel: UserViewModel,
    onRoutinesClick: () -> Unit,
    onNewsClick: () -> Unit,
    onNavigateToNews: (String) -> Unit,
    onNavigateToRoutine: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    userId: String
) {
    LaunchedEffect(userId) {
        userViewModel.fetchUserById(userId)
    }
    /*
    * Variables
    */
    val homeUiStatus by homeScreenViewModel.uiState.collectAsState()
    val context = LocalContext.current
    /*
    * This is the lifecycle of the composable
     */
    val lifecycle = LocalLifecycleOwner.current
    val news = homeScreenViewModel.news
    val routines = homeScreenViewModel.routines

    if (homeUiStatus == HomeUiStatus.Loading) {
        LoadingScreen()
    } else {
        LaunchedEffect(lifecycle) {
            when (homeUiStatus) {
                is HomeUiStatus.Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

                is HomeUiStatus.ErrorWithMessage -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

                else
                -> {
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .background(Color.White)
    ) {
        item {
            Spacer(modifier = Modifier.height(25.dp))
            HomeTitle()
        }
        item {
            Spacer(modifier = Modifier.height(25.dp))
            IMC_card(
                imc = userViewModel.user.imc,
            ) {
                onNavigateToProfile()
            }
        }
        item {
            Spacer(modifier = Modifier.height(25.dp))
            SpotifyTitle()
            Spacer(modifier = Modifier.height(25.dp))
            SpotifyCard()
        }

        item {
            Spacer(modifier = Modifier.height(25.dp))
            RoutinesTittle {
                onRoutinesClick()
            }
            Spacer(modifier = Modifier.height(15.dp))
        }

        items(
            routines.size,
        ) { index ->
            val item = routines[index]
            RoutineItemHome(
                routine = item
            ) { routineId ->
                onNavigateToRoutine(routineId)
            }
        }

        item {
            Spacer(modifier = Modifier.height(25.dp))
            NewsTittle {
                onNewsClick()
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        items(
            news.size,
            key = { index ->
                news[index].noticeId
            }
        ) { index ->
            val item = news[index]
            // Filter item
            NewItem(
                new = item,
            ) { noticeId ->
                onNavigateToNews(noticeId)
            }
        }

        item {
            Spacer(modifier = Modifier.height(110.dp))
        }

    }
}

/*
Home tittle text
*/
@Composable
fun HomeTitle() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
        text = "Home",
        fontWeight = FontWeight(700),
        fontSize = 20.sp
    )
}

/*
IMC user info
*/
@Composable
fun IMC_card(
    imc: Float,
    onNavigateToProfile: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = md_theme_light_primary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Image(painterResource(id = R.drawable.imc_card_background), contentDescription = "")
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .width(150.dp)
                                .padding(start = 22.dp, top = 22.dp, end = 22.dp, bottom = 4.dp),
                            text = "IMC (Indice de masa corporal)",
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight(600),
                            fontSize = 14.sp,
                            color = md_theme_light_onPrimary
                        )

                        Text(
                            modifier = Modifier
                                .width(170.dp)
                                .padding(start = 22.dp, top = 2.dp, end = 22.dp, bottom = 5.dp),
                            text = when{
                                imc < 21 -> "Tienes un peso bajo"
                                imc in 21.0..32.9 -> "Tienes un peso normal"
                                imc in 33.0..38.9 -> "Tienes un peso alto"
                                imc >= 39.0 -> "Tienes peso muy alto"
                                else -> "No disponible"
                            },
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight(400),
                            fontSize = 12.sp,
                            color = md_theme_light_onPrimary
                        )

                        // Button
                        Button(
                            onClick = {
                                onNavigateToProfile()
                            },
                            shape = RoundedCornerShape(10.dp),
                            elevation = ButtonDefaults.buttonElevation(8.dp),
                            modifier = Modifier
                                .padding(start = 22.dp, top = 2.dp, end = 22.dp, bottom = 22.dp)
                                .width(100.dp)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.scrim
                            )
                        ) {
                            Text(
                                text = "Ver más",
                                fontSize = 10.sp,
                            )
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 22.dp, 0.dp)
                            .drawBehind {
                                drawCircle(
                                    color = md_theme_light_secondary,
                                    radius = 50.dp.toPx()
                                )
                            },
                    ) {
                        RadialProgress(value = imc/100)
                    }
                }
            }
        }
    }
}

/*
User routines tittle
*/
@Composable
fun RoutinesTittle(
    onRoutinesClick: () -> Unit
) {
    Box(modifier = Modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        ) {

            Text(
                text = "Rutinas más recientes",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            onRoutinesClick()
                        },
                    text = stringResource(R.string.watch_more_home_screen),
                    fontSize = 12.sp,
                    color = md_theme_light_outline
                )
            }
        }
    }
}

/*
Show tree user routines
*/
@Composable()
fun RoutineItemHome(
    routine: RoutineModel,
    onRoutineClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = md_theme_light_onPrimary,
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .clickable {
                        onRoutineClick(routine.routineId)
                    }
                    .fillMaxSize()
                    .padding(17.dp)
            ) {
                // Image
                Box(
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                md_theme_light_primary,
                            )
                        }
                        .width(70.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Image
                    Image(
                        painter = painterResource(
                            when (routine.category) {
                                stringResource(R.string.full_body_category) -> R.drawable.fullbody_approach
                                stringResource(R.string.upper_body_category) -> R.drawable.upper_body_approach
                                stringResource(R.string.lower_boddy_category) -> R.drawable.lower_body_approach
                                else -> R.drawable.fullbody_approach
                            }
                        ),
                        contentDescription = "Routines Image",
                        modifier = Modifier
                            .size(70.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = routine.title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = md_theme_light_scrim
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${routine.category} | ${routine.level}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        color = md_theme_light_outline
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = "Arrow icon",
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

/*
Newest news tittle
*/
@Composable()
fun NewsTittle(
    onNewsClick: () -> Unit
) {
    Box(modifier = Modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        ) {
            Text(
                text = "Últimas noticias",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            onNewsClick()
                        },
                    text = "Ver más",
                    fontSize = 12.sp,
                    color = md_theme_light_outline
                )
            }
        }
    }
}

@Composable
fun SpotifyTitle() {
    Box(modifier = Modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        ) {
            Text(
                text = "Podcast",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
        }
    }
}

@Composable
fun SpotifyCard(
) {
    val podcastUri = "spotify:show:2czhc0HIut5fRs7uL3Waox?si=0ae7fb6c77fd4c71"
    val context = LocalContext.current
    val openPodcastLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
            // Handle the returned Uri here
        }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(16.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(podcastUri))
                        intent.putExtra(
                            Intent.EXTRA_REFERRER,
                            Uri.parse("android-app://${context.packageName}")
                        )
                        try {
                            startActivity(context, intent, null)
                        } catch (e: Exception) {
                            Toast
                                .makeText(context, "Spotify no está instalado", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spotifylogo),
                    contentDescription = "Spotify logo",
                    modifier = Modifier
                        .size(45.dp)
                        .padding(8.dp, 0.dp, 0.dp, 0.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = stringResource(R.string.podcast_name),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(0.dp, 4.dp, 0.dp, 4.dp),
                    )

                    Text(
                        text = stringResource(R.string.podcast_description),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(0.dp, 4.dp, 0.dp, 4.dp),
                    )

                    Spacer(modifier = Modifier.height(5.dp))


                    Image(
                        painter = painterResource(id = R.drawable.musicoptions),
                        contentDescription = null,
                    )

                }
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow),
                    contentDescription = "Arrow icon",
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

        }

    }

}


/*
 * Radial progress bar(used in the home screen)
 *
 * param value: Float value to be displayed in the progress bar (IMC)
 */

@Composable
fun RadialProgress(value: Float) {
    val animatedProgress = animateFloatAsState(targetValue = value)

    Canvas(modifier = Modifier.size(125.dp)) {
        val radius = size.minDimension / 2f
        val center = Offset(size.width / 2, size.height / 2)
        val startAngle = -90f
        val sweepAngle = animatedProgress.value * 360f

        drawArc(
            color = md_theme_light_secondaryContainer,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = Offset(center.x - radius, center.y - radius),
            size = Size(radius * 2, radius * 2),
            style = Fill,
            colorFilter = ColorFilter.tint(md_theme_light_secondaryContainer)
        )
    }
}


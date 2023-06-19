@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.homeScreen.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.theme.md_theme_light_onPrimary
import com.uca.polifitnessapp.ui.theme.md_theme_light_outline
import com.uca.polifitnessapp.ui.theme.md_theme_light_primary
import com.uca.polifitnessapp.ui.theme.md_theme_light_scrim
import com.uca.polifitnessapp.ui.theme.md_theme_light_secondaryContainer
import com.uca.polifitnessapp.ui.theme.spotify_color

@Preview
@Composable
fun Home() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        item{
            Spacer(modifier = Modifier.height(25.dp))
            home_tittle()
            Spacer(modifier = Modifier.height(25.dp))
            IMC_card()
        }

        item {
            Spacer(modifier = Modifier.height(25.dp))
            routinesTittle()
        }

        items(3) {
            Spacer(modifier = Modifier.height(25.dp))
            someRoutines()
        }

        item{
            Spacer(modifier = Modifier.height(25.dp))
            Spotifytitle()
            Spacer(modifier = Modifier.height(25.dp))
            SpotifyCard()
        }

        item {
            Spacer(modifier = Modifier.height(25.dp))
            newsTittle()
        }

        items(3) {
            someNews()
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }

    }
}

/*
Home tittle text
*/
@Composable
fun home_tittle() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(32.5.dp, 0.dp),
        text = "Home",
        fontWeight = FontWeight(700),
        fontSize = 20.sp
    )
}

/*
IMC user info
*/
@Composable
fun IMC_card() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.5.dp, 0.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors (
                containerColor = md_theme_light_primary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp),

            content = {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()) {

                    Column {
                        Text(modifier = Modifier
                            .width(150.dp)
                            .padding(start = 22.dp, top = 22.dp, end = 22.dp, bottom = 4.dp),
                            text = "IMC (Indice de masa corporal)",
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight(600),
                            fontSize = 14.sp,
                            color = md_theme_light_onPrimary
                        )

                        Text(modifier = Modifier
                            .width(170.dp)
                            .padding(start = 22.dp, top = 2.dp, end = 22.dp, bottom = 5.dp),
                            text = "Tienes un peso normal",
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight(400),
                            fontSize = 12.sp,
                            color = md_theme_light_onPrimary
                        )

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(start = 22.dp, top = 5.dp, end = 22.dp, bottom = 22.dp)
                                .width(90.dp),
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = md_theme_light_secondaryContainer
                            )
                        ) {
                            Text(
                                text = "Ver más",
                                fontSize = 10.sp,
                                fontWeight = FontWeight(600),
                                color = md_theme_light_outline
                            )
                        }
                    }

                    // TODO: Averiguar como implementar la gráfica de pastel
                    /*Box(
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 40.dp, 0.dp)
                            .align(Alignment.CenterVertically))
                    {

                    }*/
                }
            }
        )
    }
}

/*
User routines tittle
*/
@Composable
fun routinesTittle() {
    Box(modifier = Modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.5.dp, 0.dp)
        ) {

            Text(
                text = "Rutinas más recientes",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )

            Box(modifier = Modifier
                .align(Alignment.CenterVertically)) {
                Text(modifier = Modifier
                    .clickable { },
                    text = "Ver más",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(222),
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
fun someRoutines() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.5.dp, 0.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
            colors = CardDefaults . cardColors (
                containerColor = md_theme_light_onPrimary,
            ),

            content = {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp)) {

                    Image(modifier = Modifier
                        .clip(CircleShape)
                        .size(57.dp),
                        painter = painterResource(R.drawable.google), contentDescription = "Routine image")

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {

                        Text(
                            text = "Routine name",
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500),
                            color = md_theme_light_scrim
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "180 Calories Burn | 20 minutes",
                            fontSize = 10.sp,
                            fontWeight = FontWeight(222),
                            color = md_theme_light_outline
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        //ProgressBarDemo()
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.CenterVertically)
                            .border(
                                width = 1.dp,
                                color = md_theme_light_primary,
                                shape = CircleShape,
                            ),
                    ) {
                        FloatingActionButton(
                            onClick = { /* Acción al hacer clic en el FAB */ },
                            modifier = Modifier
                                .matchParentSize(),
                            backgroundColor = colorResource(R.color.white),
                            content = {
                                Icon(
                                    painterResource(R.drawable.nav_to_icon),
                                    contentDescription = "Ver rutina",
                                    tint = md_theme_light_primary)
                            }
                        )
                    }
                }
            }
        )
    }
}

/*
Newest news tittle
*/
@Composable()
fun newsTittle() {
    Box(modifier = Modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.5.dp, 0.dp)
        ) {

            Text(
                text = "Últimas noticias",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )

            Box(modifier = Modifier
                .align(Alignment.CenterVertically)) {
                Text(modifier = Modifier
                    .clickable { },
                    text = "Ver más",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(222),
                    color = md_theme_light_outline
                )
            }
        }
    }
}

/*
Show tree newest news
*/
@Composable
fun someNews() {
    Spacer(modifier = Modifier.height(30.dp))
    Box(modifier = Modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.5.dp, 0.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            colors = CardDefaults.cardColors (
                containerColor = md_theme_light_onPrimary,
            ),

            content = {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                )
                {
                    Image(modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(R.drawable.new_2), contentDescription = "News image")
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(modifier = Modifier
                    .padding(20.dp, 0.dp),
                    text = "Título de noticia",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(modifier = Modifier
                    .padding(20.dp, 0.dp),
                    text = "Descripción de la noticia",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(222),
                    color = md_theme_light_outline
                )

                Spacer(modifier = Modifier.height(25.dp))

                Box(modifier = Modifier
                    .align(Alignment.End)) {
                    Text(modifier = Modifier
                        .padding(20.dp, 0.dp),
                        text = "TAG de la noticia",
                        fontSize = 10.sp,
                        fontWeight = FontWeight(222),
                        color = md_theme_light_outline
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        )
    }
}

/*
Versión estática de barra de progreso

@Composable
fun ProgressBarDemo() {
    val progress = remember { mutableStateOf(0.1f) } // Valor inicial del progreso

        LinearProgressIndicator(
            progress = progress.value,
            modifier = Modifier
                .width(180.dp)
                .height(11.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = md_theme_light_primary // Color de la barra de progreso
        )
}
*/


@Composable
fun Spotifytitle() {
    Box(modifier = Modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.5.dp, 0.dp)
        ) {

            Text(
                text = "Poscast",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )

        }
    }
}
@Composable
fun SpotifyCard(
){
    val podcastUri = "spotify:show:2czhc0HIut5fRs7uL3Waox?si=0ae7fb6c77fd4c71"

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
                .padding(32.5.dp, 0.dp),
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.spotifylogo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {

                    Text(
                        text = "Ponte en Cintura",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(
                        text = "Escucha nuestro podcast en spotify.",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.musicoptions),
                        contentDescription = null,
                    )

                }

                Spacer(modifier = Modifier.width(30.dp))

                SpotifyPodcastButton(podcastUri = podcastUri)



            }

        }

    }

}

@Composable
fun SpotifyPodcastButton(podcastUri: String) {
    val context = LocalContext.current
    val openPodcastLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
        // Handle the returned Uri here
    }

    Box(
        modifier = Modifier
            .size(25.dp)
            .border(
                width = 1.dp,
                color = spotify_color,
                shape = CircleShape,)
    ) {
        FloatingActionButton(
            backgroundColor = colorResource(R.color.white),
            modifier = Modifier
                .matchParentSize(),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(podcastUri))
                intent.putExtra(Intent.EXTRA_REFERRER, Uri.parse("android-app://${context.packageName}"))

                try{
                    startActivity(context, intent, null)
                }catch (e: Exception){
                    Toast.makeText(context, "Spotify no está instalado", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Icon(
                painterResource(R.drawable.nav_to_icon),
                contentDescription = "Ir a Spotify",
                tint = spotify_color)
        }
    }
}




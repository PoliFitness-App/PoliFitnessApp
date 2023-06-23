@file:OptIn(ExperimentalMaterialApi::class)

package com.uca.polifitnessapp.ui.main.routines.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel
import com.uca.polifitnessapp.data.db.models.routine.StepModel
import com.uca.polifitnessapp.ui.navigation.components.BackButton
import com.uca.polifitnessapp.ui.navigation.components.LoadingScreen
import com.uca.polifitnessapp.ui.main.routines.viewmodel.RoutineItemViewModel
import com.uca.polifitnessapp.ui.theme.md_theme_light_error
import com.uca.polifitnessapp.ui.theme.md_theme_light_errorContainer
import com.uca.polifitnessapp.ui.theme.md_theme_light_onErrorContainer
import com.uca.polifitnessapp.ui.theme.md_theme_light_primary
import com.uca.polifitnessapp.ui.theme.md_theme_light_tertiary
import com.uca.polifitnessapp.ui.theme.spotify_color


@Composable
fun RoutineItemScreen(
    routineItemViewModel: RoutineItemViewModel,
    routineId: String,
    onBackPress : () -> Unit,
) {

    // ---
    // Fetch routine item from the routineId
    // ---

    LaunchedEffect(key1 = routineId) {
        routineItemViewModel.fetchRoutineById(routineId)
    }

    // ---
    // Loading State
    // --

    if (routineItemViewModel.isLoading.value) {
        LoadingScreen()
    } else {
        // New item screen
        val routine = routineItemViewModel.routine

        // ---
        // New item screen
        // ---
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            // Center items horizontally in the column
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                BackButton(
                    modifier = Modifier.padding(8.dp,16.dp,8.dp,16.dp),
                    onBackPress
                )
            }
            item {
                RoutineItemBox(
                    routine = routine
                )
            }
            item {
                RoutineInfo(
                    routine = routine
                )
            }
            item{
                RoutineTitle()
            }
            items(routine.steps.size) {
                RoutineStepItem(
                    step = routine.steps[it],
                    count = routine.steps.size,
                    index = it
                )
            }
            item{
               Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


/*
 * Composable Routine Steps, this composable will display the steps of the routine
 *
 * @param steps List of steps (StepModel)
 */
@Composable
fun RoutineStepItem(
    step: StepModel,
    count: Int,
    index: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, 0.dp, 8.dp, 0.dp),
        // center items horizontally in the column
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        // ---
        // Steps
        // ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            // ---
            // Steps #
            // ---
            Text(
                text = step.title,
                fontWeight = FontWeight.Normal,
                color = md_theme_light_primary,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
            )

            if (index != count - 1) {
                Image(
                    painterResource(id = R.drawable.routine_item_screen_steps),
                    contentDescription = "Steps icon",
                )
            }else{
                Image(
                    painterResource(id = R.drawable.routine_item_screen_steps_icon),
                    contentDescription = "Last step icon",
                )
            }
            // ---
            // Steps content
            // ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp, 0.dp, 8.dp, 0.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = step.description,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.scrim,
                    modifier = Modifier
                        .padding(4.dp, 0.dp, 8.dp, 0.dp)
                        .width(325.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun RoutineTitle(){
    // ---
    // Title
    // ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        // center items horizontally in the column
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.steps_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 16.dp)
        )
    }
}

@Composable
fun RoutineItemBox(
    routine: RoutineModel,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
            .aspectRatio(16 / 9f),
        // Card colors
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        ),
    ) {
        CustomExoPlayer(url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
    }
}

@Composable
fun RoutineInfo(
    routine: RoutineModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        // center items horizontally in the column
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = routine.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
        )

        Chip(
            onClick = { /*TODO*/ },
            colors = ChipDefaults.chipColors(
                backgroundColor = when (routine.level) {
                    stringResource(R.string.routine_level_easy) -> spotify_color
                    stringResource(R.string.routine_level_medium) -> md_theme_light_tertiary
                    stringResource(R.string.routine_level_hard) -> md_theme_light_error
                    stringResource(R.string.routine_level_very_hard) -> md_theme_light_error
                    else -> md_theme_light_error
                },
                contentColor = Color.White
            ),
        ) {
            Text(
                text = routine.level,
                fontWeight = FontWeight.Light,
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )
        }

        Text(
            text = "Descripción",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
        )

        Text(
            text = routine.description,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.scrim,
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                .width(325.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun CustomExoPlayer(
    url: String
) {
    val context = LocalContext.current

    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaItem = MediaItem.fromUri(Uri.parse(url))

    exoPlayer.setMediaItem(mediaItem)

    val playerView = StyledPlayerView(context)
    playerView.player = exoPlayer

    DisposableEffect(AndroidView(factory = { playerView })) {
        exoPlayer.prepare()

        // Start playing after the user has seen the preview

        onDispose {
            exoPlayer.release()
        }
    }
}

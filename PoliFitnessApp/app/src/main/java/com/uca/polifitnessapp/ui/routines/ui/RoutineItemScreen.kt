package com.uca.polifitnessapp.ui.routines.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.data.db.models.RoutineModel
import com.uca.polifitnessapp.ui.navigation.components.LoadingScreen
import com.uca.polifitnessapp.ui.news.viewmodel.NewsItemViewModel
import com.uca.polifitnessapp.ui.routines.viewmodel.RoutineItemViewModel


@Composable
fun RoutineItemScreen(
    routineItemViewModel: RoutineItemViewModel,
    navController: NavController,
    routineId: String
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

        // New item screen
        // ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            // center items horizontally in the column
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            BackButton(
                modifier = Modifier
                    .align(Alignment.Start),
                navController = navController
            )
            RoutineItemBox(
                routine = routine
            )
        }
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        // center items horizontally in the row
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                Icons.Outlined.ArrowBack,
                contentDescription = "Back button"
            )
        }
        Text(
            text = "Regresar",
            style = MaterialTheme.typography.titleSmall
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
            .aspectRatio(16f / 9f)
        ,
        // Card colors
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        ),
    ) {
        CustomExoPlayer(url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
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
        exoPlayer.playWhenReady = true

        onDispose {
            exoPlayer.release()
        }
    }
}

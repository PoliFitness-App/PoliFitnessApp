package com.uca.polifitnessapp.ui.routines.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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
    }
    else {
        // New item screen
        val routine = routineItemViewModel.routine

        // ---
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
                routine
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineItemBox(
    routine: RoutineModel,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(460.dp),
        // Card colors
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
    ) {

        // Column with title, description, category
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            // Title
            Text(
                text = routine.title,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

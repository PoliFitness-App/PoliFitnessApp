package com.uca.polifitnessapp.ui.main.news.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.ui.navigation.components.BackButton
import com.uca.polifitnessapp.ui.navigation.components.LoadingScreen
import com.uca.polifitnessapp.ui.main.news.viewmodel.NewsItemViewModel


@Composable
fun NewItemBox(
    newsItemViewModel: NewsItemViewModel,
    noticeId: String,
    onBackPress: () -> Unit
) {

    // ---
    // Fetch new item from the noticeId
    // ---

    LaunchedEffect(key1 = noticeId) {
        newsItemViewModel.fetchNewById(noticeId)
    }

    // ---
    // Loading State
    // --
    if (newsItemViewModel.isLoading.value) {
        LoadingScreen()
    }
    else {
        // New item screen
        val new = newsItemViewModel.new

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
                    .align(Alignment.Start)
                    .padding(8.dp,16.dp,8.dp,16.dp)
                ,
                onBackPress
            )
            NewItemScreen(new)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewItemScreen(
    new: NoticeModel,
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
        // Image
        GlideImage(
            model = new.image,
            contentDescription = "News",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        // Column with title, description, category
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            // Title
            Text(
                text = new.title,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = new.description,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 8.dp, 8.dp)
                    .align(Alignment.Start)
                    .height(120.dp),
                style = MaterialTheme.typography.labelSmall
            )
            // Place
            NewPlace(
                text = stringResource(R.string.place_title),
                icon = R.drawable.pin_new_screen
            )
            Spacer(modifier = Modifier.weight(1f))
            // Category
            Text(
                text = new.category,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    .align(Alignment.End)
                    .weight(1f),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun NewPlace(
    text: String,
    icon: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 0.dp, 8.dp, 4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = icon),
            contentDescription = "Calendar icon",
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
                .padding(8.dp, 0.dp, 8.dp, 8.dp),
            tint = Color.Black
        )
        Text(
            text = text,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.scrim,
            modifier = Modifier
                .padding(8.dp, 0.dp, 8.dp, 8.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
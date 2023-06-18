package com.uca.polifitnessapp.ui.news.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.ui.news.viewmodel.NewsScreenViewModel


@Composable
fun NewItemBox(
    new: NoticeModel,
    navController: NavController
) {

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
        // We call the NewItemScreen composable here
        NewItemScreen(
            new = new,
        )
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
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = new.description,
                fontWeight = FontWeight.ExtraLight,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 8.dp, 8.dp)
                    .align(Alignment.Start)
                    .height(120.dp),
                style = MaterialTheme.typography.labelSmall
            )
            // Place
            NewPlace(
                text = "Centro Deportivo UCA",
                icon = R.drawable.pin_new_screen
            )
            // Category
            Text(
                text = new.category,
                fontWeight = FontWeight.ExtraLight,
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
){
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
            fontWeight = FontWeight.ExtraLight,
            color = MaterialTheme.colorScheme.scrim,
            modifier = Modifier
                .padding(8.dp, 0.dp, 8.dp, 8.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
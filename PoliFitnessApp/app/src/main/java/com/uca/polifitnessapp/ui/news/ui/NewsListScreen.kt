package com.uca.polifitnessapp.ui.news.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.ui.navigation.components.LoadingScreen
import com.uca.polifitnessapp.ui.news.viewmodel.NewsScreenViewModel

// -----
// News List Screen
// -----

@Composable
fun NewsListScreen(
    viewModel: NewsScreenViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        // center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.isLoading.value) {
            // Loading
            LoadingScreen()
        } else {
            // News List
            NewsList(
                viewModel,
                navController
            )
        }
    }
}

// ----
// Filter Data Class
// ------

data class FilterData(
    val image: Int, //svg
    val title: String,
)

// ----
// Header Section
// ------

@Composable
fun HeaderSection(
    viewModel: NewsScreenViewModel
) {
    // List of icons
    val icon = listOf(
        FilterData(
            image = R.drawable.homeicon,
            title = "General"
        ),
        FilterData(
            image = R.drawable.sports_soccer,
            title = "Futbol"
        ),
        FilterData(
            image = R.drawable.sportssoccer,
            title = "Basketball"
        ),
        FilterData(
            image = R.drawable.sports_volleyball,
            title = "Volleyball"
        ),
        FilterData(
            image = R.drawable.sprint,
            title = "Actividades"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        //Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tittle
        Text(
            text = "Noticas Polideportivo UCA",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                .align(Alignment.Start),
        )

        // States for filter
        var selectedIndex by remember { mutableStateOf(0) }

        // on item click
        val onItemClick = { index: Int ->
            selectedIndex = index
            // filter news
            viewModel.onCategoryChange(index)
        }

        // Filter items
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(icon.size) { index ->

                // Filter item
                FilterItem(
                    icon = icon[index],
                    index = index,
                    selected = selectedIndex == index,
                    onClick = onItemClick
                )

            }
        }

    }
}

// ----
// Filter Item
// ------

@Composable
fun FilterItem(
    icon: FilterData,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .width(105.dp)
            .height(96.dp),
        // Card colors
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else Color.White,
            contentColor = if (selected) Color.White else MaterialTheme.colorScheme.outline
        ),
    ) {
        // Column with icon and text
        Column(
            Modifier
                .clickable {
                    onClick.invoke(index)
                }
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            // Icon
            Icon(
                painterResource(id = icon.image),
                contentDescription = "Filter",
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                tint = if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.outline
            )
            //Text
            Text(
                text = icon.title,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

// ----
// NewsList
// ------

@Composable
fun NewsList(
    viewModel: NewsScreenViewModel,
    navController: NavHostController
) {
    // Container
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        //Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // header (News filter)
        HeaderSection(
            viewModel
        )

        // category
        val category: String by viewModel.category.observeAsState(initial = "%")

        // List of news, comes from the viewModel
        val news = viewModel.getNews(category).collectAsLazyPagingItems()

        // News items
        LazyVerticalGrid(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 64.dp),
            columns = GridCells.Adaptive(minSize = 350.dp)
        ) {
            items(news.itemCount) { index ->
                val item = news[index]
                // Filter item
                if (item != null) {
                    NewItem(
                        new = item,
                        viewModel
                    ){noticeId ->
                        navController.navigate("new_info_screen/${noticeId}")
                    }
                }
            }
        }
    }
}

// ----
// New Item
// ------

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewItem(
    new: NoticeModel,
    viewModel: NewsScreenViewModel,
    onClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .width(350.dp)
            .height(285.dp)
            .clickable {
                viewModel.fetchNewById(new.noticeId)
                onClick(new.noticeId)
            }
        ,
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
                .width(350.dp)
                .height(165.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        // Column with title, description, category
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
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
            // Description
            val description = if (new.description.length > 40) {
                "${new.description.take(10)}..." // Agregar "..." después de los 10 caracteres
            } else {
                new.description // Mantener la descripción completa si tiene 10 caracteres o menos
            }
            Text(
                text = description,
                fontWeight = FontWeight.ExtraLight,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 8.dp, 8.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.labelSmall
            )

            // Category
            Text(
                text = new.category,
                fontWeight = FontWeight.ExtraLight,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(0.dp, 16.dp, 8.dp, 8.dp)
                    .align(Alignment.End),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

// ----
// Preview
// ------

@Preview(showBackground = true)
@Composable
fun PreviewNewsListScreen() {
    //NewsListScreen()
}
@file:OptIn(FlowPreview::class)

package com.uca.polifitnessapp.ui.news.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.ui.navigation.components.LoadingScreen
import com.uca.polifitnessapp.ui.news.viewmodel.NewsScreenViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

// -----
// News List Screen
// -----

@Composable
fun NewsListScreen(
    viewModel: NewsScreenViewModel,
    onNavigateToNews: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        // center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewsList(
            viewModel,
            onNavigateToNews
        )
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

        // on item click
        val onItemClick = { index: Int ->
            viewModel.onIndexChange(index)
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
                    selected = viewModel.selectedIndex.value == index,
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
    onNavigateToNews: (String) -> Unit
) {
    // Container
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        //Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ---
        // Header (News filter)
        // ---

        HeaderSection(
            viewModel
        )
        val coroutineScope = rememberCoroutineScope()
        // ---
        // Category
        // ---

        val category: String by viewModel.category.observeAsState(initial = "%")
        val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

        // List of news, comes from the viewModel
        // recordar el estado del pager a menos que la categoria cambie
        val news2 = remember(key1 = category) {
            viewModel.getNews(category)
        }
        val news = news2.collectAsLazyPagingItems()

        // ---
        // Vertical Grid
        // ---
        val scrollState = rememberLazyGridState(0)
        if (isLoading) {
            LoadingScreen()
        } else {
            LazyVerticalGrid(
                state = scrollState,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 64.dp),
                columns = GridCells.Adaptive(minSize = 350.dp),
            ) {
                news.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> println("Se esta recargando")
                        loadState.append is LoadState.Loading -> println("Estoy cargando en append")
                        loadState.append is LoadState.Error -> println(" Estoy en error")
                    }
                }
                items(
                    news.itemCount,
                    key = { index -> news[index]?.noticeId ?: index }
                ) { index ->
                    val item = news[index]
                    // Filter item
                    if (item != null) {
                        NewItem(
                            new = item,
                        ) { noticeId ->
                            onNavigateToNews(noticeId)
                        }
                    }
                }
            }
        }

        // Save scroll state
        /*
        * LaunchedEffect(scrollState) {
            snapshotFlow {
                scrollState.firstVisibleItemIndex
            }
                .debounce(500L)
                .collectLatest { index ->
                    if (index == 0 && viewModel.scrollState.value != 0) {
                        scrollState.animateScrollToItem(viewModel.scrollState.value)
                    }
                    viewModel.onScrollChange(index)
                }
        }
        * */

    }
}

// ----
// New Item
// ------

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewItem(
    new: NoticeModel,
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
                onClick(new.noticeId)
            },
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
                "${new.description.take(40)}..." // Agregar "..." después de los 10 caracteres
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
@file:OptIn(FlowPreview::class, ExperimentalMaterialApi::class)

package com.uca.polifitnessapp.ui.main.news.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.uca.polifitnessapp.ui.main.news.viewmodel.NewsScreenViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
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
            text = stringResource(R.string.news_title_screen),
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
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
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

        val scrollState = rememberLazyGridState(0)

        // ---
        // Pull to refresh config
        // ---

        val refreshScope = rememberCoroutineScope()
        fun refresh() = refreshScope.launch {
            viewModel.onLoadingChange(true)
            delay(1000)
            news.refresh()
            viewModel.onLoadingChange(false)
        }

        val ptrState=
            rememberPullRefreshState(isLoading, ::refresh)

        // ---
        // Vertical Grid
        // ---
        Box(
            Modifier
                .fillMaxSize()
                .pullRefresh(ptrState)
        ){
            /*
            * Laz vertical grid
             */
            LazyVerticalGrid(
                state = scrollState,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 64.dp),
                columns = GridCells.Adaptive(minSize = 350.dp),
            ) {
                val loadState = news.loadState.mediator

                /*
                // Loading
                item {
                    if (loadState?.refresh == LoadState.Loading) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    if (loadState?.append == LoadState.Loading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }

                    if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                        val isPaginatingError =
                            (loadState.append is LoadState.Error) || news.itemCount > 1
                        val error = if (loadState.append is LoadState.Error)
                            (loadState.append as LoadState.Error).error
                        else
                            (loadState.refresh as LoadState.Error).error

                        val modifier = if (isPaginatingError) {
                            Modifier.padding(8.dp)
                        } else {
                            Modifier.fillMaxSize()
                        }
                        Column(
                            modifier = modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(64.dp),
                                imageVector = Icons.Rounded.Warning, contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )

                            Text(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.scrim,
                                style = MaterialTheme.typography.labelLarge,
                                textAlign = TextAlign.Center,
                                text = "No se pudo cargar la información, revisa tu conexión a internet",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .width(250.dp),
                            )
                        }
                    }
                }
                 */

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
            /*
            * Refresh indicator
             */
            PullRefreshIndicator(refreshing = isLoading, state = ptrState, Modifier.align(Alignment.TopCenter))
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
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
            )
            // Description
            val description = if (new.description.length > 40) {
                "${new.description.take(40)}..." // Agregar "..." después de los 10 caracteres
            } else {
                new.description // Mantener la descripción completa si tiene 10 caracteres o menos
            }
            Text(
                text = description,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.scrim,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 8.dp, 8.dp)
                    .align(Alignment.Start),
            )

            Spacer(modifier = Modifier.weight(1f))

            // Category
            Text(
                text = new.category,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(0.dp, 16.dp, 8.dp, 8.dp)
                    .align(Alignment.End),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
package com.uca.polifitnessapp.ui.news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.news.data.News
import com.uca.polifitnessapp.ui.news.data.NewsViewModel
import com.uca.polifitnessapp.ui.news.data.newsList

// ----
// News List Screen
// ------

// category filter state and index size for filter
var category by mutableStateOf("")
var indexsize by mutableStateOf(0)
var indexsizefilter by mutableStateOf(0)

@Composable
fun NewsListScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        //Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // news list
        NewsList()
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
fun HeaderSection() {

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
        val onItemClick = { index: Int ->
            selectedIndex = index
            // filter news
            newsFilter(index)
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

// function for filter news
fun newsFilter(index: Int) {
    //when index
    when (index) {
        0 -> {
            // filter news by category "General"
            category = ""
        }

        1 -> {
            // filter news by category "Futbol"
            category = "Futbol"
        }

        2 -> {
            // filter news by category "Basketball"
            category = "Basketball"
        }

        3 -> {
            // filter news by category "Volleyball"
            category = "Volleyball"
        }

        4 -> {
            // filter news by category "Actividades"
            category = "Actividades"
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
) {
    // Container
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        //Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // States for news list
        var selectedIndex by remember { mutableStateOf(0) }
        val onItemClick = { index: Int ->
            selectedIndex = index
        }

        if (category.isBlank())
        // index size for news list
            indexsize = newsList.size
        else
        // index size for filter list
            indexsize = newsList.filter { it.category == category }.size

        // News items
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 64.dp)
        ) {
            item {
                // header (News filter)
                HeaderSection()
            }
            items(indexsize) { index ->
                // Filter item
                NewItem(
                    new = if (category.isBlank())
                        newsList[index]
                    else
                        newsList.filter { it.category == category }[index],
                    index = index,
                    selected = selectedIndex == index,
                    onClick = onItemClick
                )

            }
        }

    }
}

// ----
// New Item
// ------

@Composable
fun NewItem(
    new: News,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .width(350.dp)
            .height(285.dp),
        // Card colors
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
    ) {
        // Image

        Image(
            painter = painterResource(id = new.image),
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
                .clickable {
                    onClick.invoke(index)
                }
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
            Text(
                text = new.description,
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
                    .padding(8.dp, 16.dp, 8.dp, 8.dp)
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
    NewsListScreen()
}
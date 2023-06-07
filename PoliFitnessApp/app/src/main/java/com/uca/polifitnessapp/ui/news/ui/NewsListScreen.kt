package com.uca.polifitnessapp.ui.news.ui

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
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.theme.PrimaryColor
import com.uca.polifitnessapp.ui.theme.SecondaryColor

@Composable
fun NewsListScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        //Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // header (News filter)
        HeaderSection()

        // news list

        //NewsList()
    }

}

data class FilterData(
    val image: Int , //svg
    val title: String,
)

@Composable
fun HeaderSection() {

    // List of icons
    val icon = listOf<FilterData>(
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
                .padding(16.dp)
                .align(Alignment.Start),
            )

        // States for filter
        var selectedIndex by remember { mutableStateOf(0) }
        val onItemClick = { index: Int -> selectedIndex = index}

        // Filter
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(icon.size){index ->

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
    //
}

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
            .height(96.dp)
        ,
        // Card colors
        colors = CardDefaults.cardColors(
            containerColor = if (selected) PrimaryColor else Color.White,
            contentColor = if (selected) Color.White else SecondaryColor
        ),
    ) {
        // Column with icon and text
        Column(
            Modifier
                .clickable {
                    onClick.invoke(index)
                }
                .fillMaxSize()
                .padding(8.dp)
            ,
            verticalArrangement = Arrangement.Center,
        ) {
            // Icon
            Icon(
                painterResource(id = icon.image) ,
                contentDescription = "Filter",
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                tint = if (selected) Color.White else SecondaryColor
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

@Composable
fun NewsList() {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsListScreen(){
    HeaderSection()
}
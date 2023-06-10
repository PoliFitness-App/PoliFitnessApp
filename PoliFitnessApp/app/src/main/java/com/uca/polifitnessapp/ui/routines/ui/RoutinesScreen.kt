package com.uca.polifitnessapp.ui.routines.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Filter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.RoutineModel
import com.uca.polifitnessapp.ui.routines.data.routinesList


// Main Screen for Routines
@Composable
fun RoutinesListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        // Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // routines list
        RoutinesList()
    }

}

// States for routines list
var indexsize by mutableStateOf(0)
var indexsizefilter by mutableStateOf(0)
var level by mutableStateOf("")
var category by mutableStateOf("")

// Routine list component
@Composable
fun RoutinesList(
) {
    // Container
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        //Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // States for news list
        var selectedIndex by remember { mutableStateOf(0) }
        val onItemClick = { index: Int ->
            selectedIndex = index
        }

        // Filter's for news list
        // Filter by level
        if (level == "") {
            indexsize = routinesList.size
        } else
            indexsize = routinesList.filter { it.level == level }.size

        // Filter by category
        if (category == "") {
            indexsizefilter = routinesList.size
        } else
            indexsizefilter = routinesList.filter { it.category == category }.size


        // Recomended routines list
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 64.dp)
        ) {
            item {
                // Tittle
                Text(
                    text = "Rutinas",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(16.dp, 8.dp, 16.dp, 8.dp)
                        .align(Alignment.Start),
                )
            }

            // Filter por recomended routines
            item {
                FilterItem(
                    text = "Rutinas recomendadas",
                    items = listOf(
                        "Nivel 0",
                        "Nivel 1",
                        "Nivel 2",
                        "Nivel 3",
                        "Nivel 4"
                    ),
                )
            }

            // List of recomended routines
            items(indexsize) { index ->
                // Filter item
                RoutineItem(
                    routine = if (level.isBlank()) routinesList[index] else routinesList.filter { it.level == level }[index],
                    index = index,
                    selected = selectedIndex == index,
                    onClick = onItemClick
                )
            }

            // Filter por general routines

            item {
                FilterItem(
                    text = "Rutinas de ejercicio",
                    items = listOf(
                        "Cuerpo completo",
                        "Tren superior",
                        "Tren inferior"
                    ),
                )
            }

            // List of general routines
            items(indexsizefilter) { index ->
                // Filter item
                RoutineItem(
                    routine = if (category.isBlank()) routinesList[index] else routinesList.filter { it.category == category }[index],
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
fun RoutineItem(
    routine: RoutineModel,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .width(350.dp)
            .height(155.dp),
        // Card colors
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
    ) {

        // Row that contains image and column with title, description, button
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Column with title, description, button
            Column(
                Modifier
                    .width(200.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                // Title
                Text(
                    text = routine.name,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                    style = MaterialTheme.typography.bodySmall
                )
                // Description
                Text(
                    text = routine.approach,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(8.dp, 0.dp, 8.dp, 8.dp)
                        .align(Alignment.Start),
                    style = MaterialTheme.typography.labelSmall
                )
                // Button
                Button(
                    onClick = { onClick.invoke(index) },
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .width(100.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.scrim
                    )
                ) {
                    Text(
                        text = "Ver más",
                        fontSize = 10.sp,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            Color(0xFFD7E2FF),
                        )
                    }
                    .width(100.dp),
                contentAlignment = Alignment.Center
            ) {
                // Image
                Image(
                    painter = painterResource(R.drawable.fullbody_approach),
                    contentDescription = "Routines Image",
                    modifier = Modifier
                        .width(82.dp)
                        .height(124.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

// ----
// New Item
// ------

@Composable
fun FilterItem(
    text: String,
    items: List<String>,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Title
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        // Icon button for filter
        IconButton(
            onClick = { expanded = true }
        ) {
            Icon(
                painterResource(id = R.drawable.filter_icon),
                contentDescription = "Filter",
                tint = Color.Black
            )
        }

        // DoropDownMenu when icon button is clicked
        DropdownMenu(
            modifier = Modifier
                .wrapContentSize(Alignment.TopEnd)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .animateContentSize()
            ,
            expanded = expanded,
            onDismissRequest = { expanded = false },
            properties = PopupProperties(clippingEnabled = false),
            offset = DpOffset(x = 100.dp, y = 0.dp),
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        when (label) {
                            "Nivel 0" -> level = "0"
                            "Nivel 1" -> level = "1"
                            "Nivel 2" -> level = "2"
                            "Nivel 3" -> level = "3"
                            "Nivel 4" -> level = "4"
                            "Cuerpo completo" -> category = "Cuerpo completo"
                            "Tren superior" -> category = "Tren superior"
                            "Tren inferior" -> category = "Tren inferior"
                        }
                    }
                ) {
                    Text(
                        text = label,
                        color = Color.Black,
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}


// ----
// Preview
// ------

@Preview(showBackground = true)
@Composable
fun PreviewNewsListScreen() {
    RoutinesList()
}

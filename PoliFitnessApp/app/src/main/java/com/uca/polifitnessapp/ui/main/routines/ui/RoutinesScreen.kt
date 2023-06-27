@file:OptIn(FlowPreview::class, ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.uca.polifitnessapp.ui.main.routines.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.paging.compose.collectAsLazyPagingItems
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel
import com.uca.polifitnessapp.ui.main.routines.viewmodel.RoutinesViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Composable Routines list screen
 * @Description: Routines list screen, with the list of routines and filters
 *
 * @param: viewModel: RoutinesViewModel
 * @param: userViewModel: UserViewModel
 * @param: onNavigateToRoutine: (String) -> Unit , function to navigate to routine detail
 **/

@Composable
fun RoutinesListScreen(
    viewModel: RoutinesViewModel,
    userViewModel: UserViewModel,
    onNavigateToRoutine: (String) -> Unit
) {
    // Fetch user from the userId
    LaunchedEffect(Unit) {
        try {
            userViewModel.getUserInfo()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        // Center items horizontally in the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // routines list
        RoutinesList(
            viewModel,
            userViewModel,
            onNavigateToRoutine
        )
    }
}


/**
 * @Composable Header section filter
 * @Description: Header section with routines filter
 *
 * @param: viewModel: RoutinesViewModel
 **/

@Composable
fun HeaderSectionFilter(
    viewModel: RoutinesViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp, 0.dp, 0.dp),
        // Center items horizontally in the column
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 8.dp),
            text = "Rutinas",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall
        )
        FilterItem(
            "Filtrar por nivel",
            items = listOf(
                "Todos",
                "Fácil",
                "Medio",
                "Difícil",
                "Muy difícil"
            )
        ) {
            viewModel.onLevelChange(it)
        }
        FilterItem(
            "Filtrar por categoria",
            items = listOf(
                "Todos",
                "Tren superior",
                "Tren inferior",
                "Cuerpo completo"
            ),
        ) {
            viewModel.onCategoryChange(it)
        }
    }
}

/**
 * @Composable Routines list section
 * @Description: Routines list
 *
 * @param: viewModel: RoutinesViewModel
 * @param: userViewModel: UserViewModel
 * @param: onNavigateToRoutine: (String) -> Unit , function to navigate to routine detail
 **/

/**
 * @Composable Filter item
 * @Description: Filter button component
 *
 * @param: text: String, text to show in the filter
 * @param: items: List<String>, list of items to show in the dropdown menu
 * @param: onClick: (String) -> Unit , function to select a filter
 **/

@Composable
fun FilterItem(
    text: String,
    items: List<String>,
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .width(200.dp),
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
                .animateContentSize(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            properties = PopupProperties(clippingEnabled = false),
            offset = DpOffset(x = 100.dp, y = 0.dp),
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        // On Category change
                        onClick(label)
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

@ExperimentalMaterialApi
@Composable
fun RoutinesList(
    viewModel: RoutinesViewModel,
    userViewModel: UserViewModel,
    onNavigateToRoutine: (String) -> Unit
) {

    // Scroll state
    val scrollState = rememberLazyGridState(0)

    // Initialize states for filters
    val category: String by viewModel.category.observeAsState(initial = "%")
    val approach = userViewModel.user.approach
    val level: String by viewModel.level.observeAsState(initial = "%")
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    // Filter's for news list
    // Filter by level

    val routinesByFilters2 = remember(key1 = category, key2 = level) {
        viewModel.getRoutinesByApproachAndCategoryAndLevel(
            approach, category, level
        )
    }
    val routinesByFilters = routinesByFilters2.collectAsLazyPagingItems()

    // Recommended routines list

    val refreshScope = rememberCoroutineScope()
    fun refresh() = refreshScope.launch {
        viewModel.onLoadingChange(true)
        delay(1000)
        routinesByFilters.refresh()
        viewModel.onLoadingChange(false)
    }

    val ptrState =
        rememberPullRefreshState(isLoading, ::refresh) // 1

    // ---
    // Filter
    // ---

    HeaderSectionFilter(
        viewModel = viewModel
    )

    // ---
    //Routines section, with pull to refresh
    // ---
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(ptrState),
    ) {

        /*
         * Lazy Vertical Grid
         */
        LazyVerticalGrid(
            state = scrollState,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 64.dp),
            columns = GridCells.Adaptive(minSize = 350.dp),
        ) {
            // List of recommended routines
            items(count = routinesByFilters.itemCount) { index ->
                val item = routinesByFilters[index]

                if (item != null) {
                    // Filter item
                    RoutineItem(
                        routine = item
                    ) { routineId ->
                        onNavigateToRoutine(routineId)
                    }
                }
            }
        }

        /*
        * Refresh indicator
        */
        PullRefreshIndicator(
            refreshing = isLoading,
            state = ptrState,
            Modifier.align(Alignment.TopCenter),
            scale = true,
        )
    }
}


/**
 * @Composable Routine Item
 * @Description: Routine item component
 *
 * @param: viewModel: RoutinesViewModel
 * @param: onClick: (String) -> Unit , function to navigate to routine detail
 **/

@Composable
fun RoutineItem(
    routine: RoutineModel,
    onClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp)
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
                    text = routine.title,
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
                    onClick = {
                        onClick(routine.routineId)
                    },
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
                    painter = painterResource(
                        when (routine.category) {
                            stringResource(R.string.full_body_category) -> R.drawable.fullbody_approach
                            stringResource(R.string.upper_body_category) -> R.drawable.upper_body_approach
                            stringResource(R.string.lower_boddy_category) -> R.drawable.lower_body_approach
                            else -> R.drawable.fullbody_approach
                        }
                    ),
                    contentDescription = "Routines Image",
                    modifier = Modifier
                        .width(82.dp)
                        .height(124.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

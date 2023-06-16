package com.uca.polifitnessapp.ui.navigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

// BOTTOM BAR

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
) {
    val screens = listOf(
        ButtomNavItems.Home,
        ButtomNavItems.News,
        ButtomNavItems.Rutine,
        ButtomNavItems.Profile
    )

    // Current back stack entry
    val navStackbacEntry by navController.currentBackStackEntryAsState()

    // CurrentDestination
    val currentDestination = navStackbacEntry?.destination

    // Bottom bar, if bottomBarState is true, the bottom bar is visible
    AnimatedVisibility(
        visible = bottomBarState.value
    ) {

        // Row with the bottom bar items
        Row(
            modifier = Modifier
                .padding(10.dp, 10.dp, 10.dp)
                .background(Color.White, RoundedCornerShape(20.dp, 20.dp))
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // HOME

            AddItem(
                screen = screens[0],
                currentDestination = currentDestination,
                navController = navController
            )

            // NEWS

            AddItem(
                screen = screens[1],
                currentDestination = currentDestination,
                navController = navController
            )

            // CALCULATOR

            CalculatorButton()

            // RUTINE

            AddItem(
                screen = screens[2],
                currentDestination = currentDestination,
                navController = navController
            )

            // PROFILE

            AddItem(
                screen = screens[3],
                currentDestination = currentDestination,
                navController = navController
            )

        }

    }
}

// CALCULATOR BUTTON

@Composable
fun CalculatorButton() {
    FloatingActionButton(
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .offset(y = (-10).dp),
        onClick = {

        },
        containerColor = Color(0xFF034189),
        contentColor = Color.White,
        shape = RoundedCornerShape(50.dp),
        elevation = FloatingActionButtonDefaults.elevation(10.dp, 10.dp)
    ) {
        Icon(imageVector = Icons.Outlined.Calculate, contentDescription = "")
    }
}

@Composable
// AddItem function, this function is used to add the items to the bottom bar
fun RowScope.AddItem(
    // Screen
    screen: ButtomNavItems,
    // Current destination
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    // Selected state of the item
    val selected = currentDestination?.hierarchy?.any { it.route == screen.rute } == true

    // Box with the item
    Box(
        modifier = Modifier
            .height(48.dp)
            .width(54.dp)
            .clip(shape = RoundedCornerShape(60.dp))
            .clickable {
                // Navigate to the screen when the item is clicked, and pop up to the start destination
                // of the graph to avoid building up a large stack of destinations on the back stack as users select items
                navController.navigate(screen.rute) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                .height(48.dp)
                .width(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // If the item is selected, the background color is blue, if not, the background color is white
            AnimatedVisibility(
                visible = selected,
                enter = scaleIn(
                    animationSpec = tween(300, 100, LinearOutSlowInEasing),
                    initialScale = 0.8f
                ),
                exit = scaleOut(
                    animationSpec = tween(300, 300, LinearOutSlowInEasing),
                    targetScale = 0.8f
                ),
                modifier = Modifier.animateContentSize()
            ) {
                Icon(
                    painter = painterResource(id = screen.iconFocus),
                    contentDescription = "",
                    tint = Color(0xFF034189),
                    modifier = Modifier
                        .sizeIn(48.dp)
                )
            }
            AnimatedVisibility(
                visible = !selected,
                enter = scaleIn(
                    animationSpec = tween(300, 100, LinearOutSlowInEasing),
                    initialScale = 0.8f
                ),
                exit = scaleOut(
                    animationSpec = tween(300, 300, LinearOutSlowInEasing),
                    targetScale = 0.8f
                ),
                modifier = Modifier.animateContentSize()
            ) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "",
                    modifier = Modifier
                        .sizeIn(48.dp)
                )
            }
        }
    }
}

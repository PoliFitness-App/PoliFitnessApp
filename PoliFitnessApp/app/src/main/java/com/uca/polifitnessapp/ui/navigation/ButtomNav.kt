package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.ui.LoginViewModel

// BOTTOM BAR

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        ButtomNavItems.Home,
        ButtomNavItems.News,
        ButtomNavItems.Rutine,
        ButtomNavItems.Profile
    )

    val navStackbacEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackbacEntry?.destination

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

// CALCULATOR BUTTON

@Composable
fun CalculatorButton() {
    FloatingActionButton(
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .offset(y = (-10).dp),
        onClick = {},
        containerColor = Color(0xFF034189),
        contentColor = Color.White,
        shape = RoundedCornerShape(50.dp),
        elevation = FloatingActionButtonDefaults.elevation(10.dp, 10.dp)
    ) {
        Icon(imageVector = Icons.Outlined.Calculate, contentDescription = "")
    }
}

@Composable
fun RowScope.AddItem(
    screen: ButtomNavItems,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.rute } == true
    Box(
        modifier = Modifier
            .height(48.dp)
            .clip(CircleShape)
            .clickable {
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

            AnimatedVisibility(visible = selected, enter = fadeIn()) {
                Icon(
                    painter = painterResource(id = screen.iconFocus),
                    contentDescription = "",
                    tint = Color(0xFF034189),
                    modifier = Modifier
                        .sizeIn(48.dp)
                        .animateContentSize()
                )
            }
            AnimatedVisibility(visible = !selected, enter = fadeIn()) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "",
                    modifier = Modifier
                        .sizeIn(48.dp)
                        .animateContentSize()
                )
            }
        }
    }
}

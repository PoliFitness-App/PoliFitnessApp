package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.ui.LoginViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ButtonBar(navController: NavHostController) {

    val navigation_item = listOf(
        NavItems.Home,
        NavItems.News,
        NavItems.Rutine,
        NavItems.Profile
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(navController, navigation_item)
        }
    ) {
        LoginScreen(LoginViewModel())

        NavigationHost(navController)
    }

}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    menu_items: List<NavItems>
) {

    // BottomNavigation

    BottomAppBar(
        backgroundColor = Color.White,
        modifier = Modifier
            .height(83.dp),
        contentColor = Color.Black
    ) {

        val currentRoute = currentRoute(navController)

        menu_items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    if (currentRoute == item.rute)
                        Icon(
                            painter = painterResource(id = item.iconFocus),
                            contentDescription = "",
                            tint = Color(0xFF034189),
                            modifier = Modifier
                                .width(36.dp)
                                .height(36.dp)
                                .weight(2f)
                        )
                    else {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(36.dp)
                                .height(36.dp)
                                .weight(2f)
                        )
                    }
                },
                selected = currentRoute == item.rute,
                onClick = {
                    navController.navigate(item.rute)

                }
            )
        }

        FloatingActionButton(
            onClick = {},
            containerColor = Color(0xFF034189),
            contentColor = Color.White,
            shape = RoundedCornerShape(50.dp),
            elevation = FloatingActionButtonDefaults.elevation(10.dp, 10.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Calculate, contentDescription = "")
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {

    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route

}
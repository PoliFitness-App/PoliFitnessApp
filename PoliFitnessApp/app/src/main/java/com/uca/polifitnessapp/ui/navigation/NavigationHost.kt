package com.uca.polifitnessapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.uca.polifitnessapp.ui.navigation.ItemsMenu.*

@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(navController = navController,
        startDestination = ItemsMenu.Home.rute
    ) {
        composable(Home.rute) {
            // HomeScreen()
        }
        composable(News.rute) {
            // NewsScreen()
        }
        composable(Rutine.rute) {
            // RutineScreen()
        }
        composable(Profile.rute) {
            // ProfileScreen()
        }
    }

}
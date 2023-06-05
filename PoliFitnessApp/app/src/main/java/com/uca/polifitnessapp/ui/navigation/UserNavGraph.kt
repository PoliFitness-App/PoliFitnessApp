package com.uca.polifitnessapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.ui.EditProfile.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.UserProfile.ui.ProfileScreen

@Composable
fun UserNavigation(){
    
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = UserScreens.UserProfileScreen.route) {

        composable(route = UserScreens.UserProfileScreen.route){
            ProfileScreen(navController)
        }

        composable(route = UserScreens.EditProfileScreen.route){
            EditProfileScreen()
        }

    }

}
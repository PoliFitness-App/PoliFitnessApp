package com.uca.polifitnessapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.EditProfile.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.UserProfile.ui.ProfileScreen

@Composable
fun UserNavigation(){
    
    val navController = rememberNavController()
    val user = UserModel("Fitness app", "ucafitnessapp@uca.edu.sv", 162.0, 50.0, 22, 50.0,53.0 )

    NavHost(navController = navController,
        startDestination = UserScreens.UserProfileScreen.route) {

        composable(route = UserScreens.UserProfileScreen.route){
            ProfileScreen()
        }

        composable(route = UserScreens.EditProfileScreen.route){
            EditProfileScreen()
        }

    }

}
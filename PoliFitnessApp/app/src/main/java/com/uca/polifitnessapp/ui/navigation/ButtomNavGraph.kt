package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.EditProfile.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.UserProfile.ui.ProfileScreen
import com.uca.polifitnessapp.ui.loadingscreen.onboardscreen.ui.MainFunction
import com.uca.polifitnessapp.ui.loadingscreen.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.ui.LoginViewModel

import com.uca.polifitnessapp.ui.navigation.ButtomNavItems.*
import com.uca.polifitnessapp.ui.news.ui.NewsListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationHost(navController: NavHostController) {

    val user = UserModel("Fitness app", "ucafitnessapp@uca.edu.sv", 162F, 50F, 21, 0.3F, 0.2f)

    NavHost(
        navController = navController,
        startDestination = "auth_flow"
    ) {

        // ---
        // NEW USER FLOW
        // ---

        navigation(
            startDestination = "splash_screen",
            route = "new_user_flow"
        ) {
            composable("splash_screen") {
                AnimatedSplashScreen(navController = navController)
            }
            composable("onboard_screen") {
                MainFunction(navController = navController)
            }
            composable("register_screen") {
                LoginScreen(viewModel = LoginViewModel())
            }
        }

        // ---
        // USER FLOW
        // ---

        navigation(
            startDestination = "splash_screen",
            route = "auth_flow"
        ) {
            composable("splash_screen") {
                AnimatedSplashScreen(navController = navController)
            }
            composable("login_screen") {
                LoginScreen(viewModel = LoginViewModel())
            }
        }

        // ---
        // MAIN FLOW
        // ---

        navigation(
            startDestination = Home.rute,
            route = "main_flow"
        ) {
            // Home route
            composable(Home.rute) {
                PreviewScreens(greeting = "Home Screen")
            }
            // News route
            composable(News.rute) {
                NewsListScreen()
            }
            // Routine route
            composable(Rutine.rute) {
                PreviewScreens(greeting = "Routine Screen")
            }
            // Profile route
            composable(Profile.rute) {
                ProfileScreen(navController, user)
            }
            // Edit profile route
            composable(UserScreens.EditProfileScreen.route) {
                EditProfileScreen()
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


@Composable
fun PreviewScreens(
    greeting: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greeting)
    }
}
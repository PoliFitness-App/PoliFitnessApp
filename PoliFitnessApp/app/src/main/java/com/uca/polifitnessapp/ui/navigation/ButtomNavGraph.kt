package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.EditProfile.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.UserProfile.ui.ProfileScreen
import com.uca.polifitnessapp.ui.onboardscreen.ui.MainFunction
import com.uca.polifitnessapp.ui.loadingscreen.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel

import com.uca.polifitnessapp.ui.navigation.ButtomNavItems.*
import com.uca.polifitnessapp.ui.news.ui.NewsListScreen
import com.uca.polifitnessapp.ui.routines.ui.RoutinesListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationHost(navController: NavHostController) {

    val user = UserModel("Fitness app", "ucafitnessapp@uca.edu.sv", 162F, 50F, 21, 0.3F, 0.2f)

    // login view model
    val loginViewModel: LoginViewModel = viewModel(
    factory = LoginViewModel.Factory
    )

    NavHost(
        navController = navController,
        startDestination = "login_screen"
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
                LoginScreen(viewModel = loginViewModel, navController = navController)
            }
        }

        composable("login_screen") {
            LoginScreen(viewModel = loginViewModel, navController = navController)
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
                LoginScreen(viewModel = loginViewModel, navController = navController)
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
                RoutinesListScreen()
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
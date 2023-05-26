package com.uca.polifitnessapp.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.ui.LoadingScreen.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.ui.LoginViewModel

import com.uca.polifitnessapp.ui.navigation.ButtomNavItems.*

@Composable
fun NavigationHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
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
        composable("splash_screen"){
            AnimatedSplashScreen(navController= navController)
        }
        composable("login_screen"){
            LoginScreen(viewModel = LoginViewModel())
        }
        composable("home_screen"){

            // SOLO DE PRUEBA POR EL MOMENTO

            // TODO : CAMBIAR POR EL HOME SCREEN

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text ="Home Screen")
            }
        }
    }
}
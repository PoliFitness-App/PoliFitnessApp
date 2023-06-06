package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.ui.loadingscreen.onboardscreen.ui.MainFunction
import com.uca.polifitnessapp.ui.loadingscreen.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.ui.LoginViewModel

import com.uca.polifitnessapp.ui.navigation.ButtomNavItems.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboard_screen"
    ) {
        composable(Home.rute) {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController)
                }
            ) {
                PreviewScreens(greeting = "Home Screen")
            }
        }
        composable(News.rute) {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController)
                }
            ) {
                PreviewScreens(greeting = "News Screen")
            }
        }
        composable(Rutine.rute) {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController)
                }
            ) {
                PreviewScreens(greeting = "Routine Screen")
            }
        }
        composable(Profile.rute) {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController)
                }
            ) {
                PreviewScreens(greeting = "Profile Screen")
            }
        }
        composable("onboard_screen"){
            MainFunction(navController= navController)
        }
        composable("splash_screen"){
            AnimatedSplashScreen(navController= navController)
        }
        composable("login_screen"){
            LoginScreen(viewModel = LoginViewModel())
        }
    }
}

@Composable
fun PreviewScreens(
    greeting:String
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greeting)
    }
}
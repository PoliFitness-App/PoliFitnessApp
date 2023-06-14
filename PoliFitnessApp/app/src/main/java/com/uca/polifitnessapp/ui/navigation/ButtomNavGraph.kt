package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.EditProfile.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.UserProfile.ui.ProfileScreen
import com.uca.polifitnessapp.ui.onboardscreen.ui.MainFunction
import com.uca.polifitnessapp.ui.loadingscreen.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel

import com.uca.polifitnessapp.ui.navigation.ButtomNavItems.*
import com.uca.polifitnessapp.ui.news.data.NewsViewModel
import com.uca.polifitnessapp.ui.news.ui.NewsListScreen
import com.uca.polifitnessapp.ui.routines.ui.RoutinesListScreen
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationHost(navController: NavHostController) {

    val user = UserModel("Fitness app", "ucafitnessapp@uca.edu.sv", 162F, 50F, 21, 0.3F, 0.2f)

    // login view model
    val loginViewModel: LoginViewModel = viewModel(
    factory = LoginViewModel.Factory
    )

    val newsViewModel: NewsViewModel = viewModel(
        factory = NewsViewModel.Factory
    )

    NavHost(
        navController = navController,
        startDestination = "main_flow"//login_screen
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
                PreviewScreens(greeting = "Home Screen", viewModel = newsViewModel)
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
    greeting: String,
    viewModel: NewsViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greeting)
        //Log.d("News", "News: ${viewModel.news}")
        val coroutineScope = rememberCoroutineScope()
        val news = viewModel.getNews("%").collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            println("IMPRESION DE LAS NOTICIAS----------------------------------")
            println(news)
            items(count = news.itemCount) { index ->
                val item = news[index]
                if (item != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Text(
                            text = item.title,

                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
            println("TERMINA LA IMPRESION DE LAS NOTICIAS--------------------------------")
        }

    }
}
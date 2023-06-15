package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.EditProfile.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.EditProfile.viewmodel.EditProfileViewModel
import com.uca.polifitnessapp.ui.UserProfile.ui.ProfileScreen
import com.uca.polifitnessapp.ui.onboardscreen.ui.MainFunction
import com.uca.polifitnessapp.ui.loadingscreen.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.uca.polifitnessapp.ui.navigation.ButtomNavItems.*
import com.uca.polifitnessapp.ui.news.data.NewsViewModel
import com.uca.polifitnessapp.ui.news.ui.NewsListScreen
import com.uca.polifitnessapp.ui.news.viewmodel.NewsScreenViewModel
import com.uca.polifitnessapp.ui.routines.ui.RoutinesListScreen
import com.uca.polifitnessapp.ui.signup.approachscreen.ui.SignUpGoalScreen
import com.uca.polifitnessapp.ui.signup.personalinfoscreen.SignUpPersonalInfoScreen
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import com.uca.polifitnessapp.ui.signup.signupscreen.SignUpScreen
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpGoalViewModel
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpPersonalInfoViewModel
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpViewModel
import com.uca.polifitnessapp.ui.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationHost(navController: NavHostController) {

    // ---
    // Instance of view models
    // ---

    // Login view model
    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.Factory
    )

    // News view model
    val newsScreenViewModel: NewsScreenViewModel = viewModel(
        factory = NewsScreenViewModel.Factory
    )

    // User view model (global)
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModel.Factory
    )

    // Edit profile view model
    val editProfileViewModel: EditProfileViewModel = viewModel(
        factory = EditProfileViewModel.Factory
    )

    // ---
    // Sign up view models
    // ---
    // Sign up personal info view model
    val signUpPersonalViewModel: SignUpPersonalInfoViewModel = viewModel(
        factory = SignUpPersonalInfoViewModel.Factory
    )

    // Sign up view model
    val signUpViewModel: SignUpViewModel = viewModel(
        factory = SignUpViewModel.Factory
    )

    // Sign up goal view model
    val signUpGoalViewModel: SignUpGoalViewModel = viewModel(
        factory = SignUpGoalViewModel.Factory
    )

    // ---
    // Nav Host
    // ---

    NavHost(
        navController = navController,
        startDestination = "new_user_flow"//login_screen
    ) {

        // ---
        // New user flow
        // ---

        // This flow contains the onboarding screen and the register screen
        // The register screen is the first screen that the user will see after the onboarding screen
        // The onboarding screen is the first screen that the user will see if the user is not logged in

        navigation(
            // startDestination = "onboard_screen",
            startDestination = "register_screen",
            // route = new user flow
            route = "new_user_flow"
        ) {
            composable("splash_screen") {
                AnimatedSplashScreen(navController = navController)
            }
            composable("onboard_screen") {
                MainFunction(navController = navController)
            }
            composable("sign_up_screen") {
                // TODO - Register screen
                SignUpScreen(
                    navController,
                    signUpViewModel,
                    userViewModel
                )
            }
            composable("personal_info_screen") {
                SignUpPersonalInfoScreen(
                    navController,
                    signUpPersonalViewModel,
                    userViewModel
                )
            }
            composable("approach_screen") {
                SignUpGoalScreen(
                    navController,
                    signUpGoalViewModel,
                    userViewModel
                )
            }
        }

        composable("login_screen") {
            LoginScreen(
                viewModel = loginViewModel,
                userViewModel = userViewModel,
                navController = navController
            )
        }

        // ---
        // User flow
        // ---

        // This flow is for users that are already registered
        // The user will be redirected to this flow if the user is already logged in

        navigation(
            // start destination = "splash_screen",
            startDestination = "splash_screen",
            route = "auth_flow"
        ) {
            composable("splash_screen") {
                AnimatedSplashScreen(navController = navController)
            }
            composable("login_screen") {
                LoginScreen(
                    viewModel = loginViewModel,
                    userViewModel = userViewModel,
                    navController = navController
                )
            }
        }

        // ---
        // Main flow
        // ---

        // This flow is for the main screens of the app
        // The user will be redirected to this flow if the user is already logged in

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
                NewsListScreen(
                    viewModel = newsScreenViewModel
                )
            }
            // Routine route
            composable(Rutine.rute) {
                RoutinesListScreen()
            }
            // Profile route
            composable(Profile.rute) {
                ProfileScreen(navController, userViewModel)
            }
            // Edit profile route
            composable(UserScreens.EditProfileScreen.route) {
                EditProfileScreen(
                    navController,
                    userViewModel,
                    editProfileViewModel
                )
            }
        }
    }
}


// TODO - Remove this function
@Composable
fun PreviewScreens(
    greeting: String,
) {
    //
}
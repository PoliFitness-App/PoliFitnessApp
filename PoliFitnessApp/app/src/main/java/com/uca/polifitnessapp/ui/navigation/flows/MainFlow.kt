package com.uca.polifitnessapp.ui.navigation.flows

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.uca.polifitnessapp.ui.user.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.user.viewmodel.EditProfileViewModel
import com.uca.polifitnessapp.ui.user.ui.ProfileScreen
import com.uca.polifitnessapp.ui.navigation.PreviewScreens
import com.uca.polifitnessapp.ui.navigation.components.ButtomNavItems
import com.uca.polifitnessapp.ui.news.ui.NewsListScreen
import com.uca.polifitnessapp.ui.news.viewmodel.NewsScreenViewModel
import com.uca.polifitnessapp.ui.routines.ui.RoutinesListScreen
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel

// ---
// Main flow
// ---

// This flow is for the main screens of the app
// The user will be redirected to this flow if the user is already logged in

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
    editProfileViewModel: EditProfileViewModel,
    newsScreenViewModel: NewsScreenViewModel,
    userViewModel: UserViewModel
){
    navigation(
        startDestination = ButtomNavItems.Home.rute,
        route = MainRoutes.MAIN_ROUTE
    ) {
        // Home route
        composable(ButtomNavItems.Home.rute) {
            PreviewScreens(greeting = "Home Screen")
        }
        // News route
        composable(ButtomNavItems.News.rute) {
            NewsListScreen(
                newsScreenViewModel
            )
        }
        // Routine route
        composable(ButtomNavItems.Rutine.rute) {
            RoutinesListScreen()
        }
        // Profile route
        composable(ButtomNavItems.Profile.rute) {
            ProfileScreen(
                navController,
                userViewModel
            )
        }
        // Edit profile route
        composable(MainRoutes.MAIN_USER_EDIT) {
            EditProfileScreen(
                navController,
                userViewModel,
                editProfileViewModel
            )
        }
    }
}

// ---
// Routes
// ---

object MainRoutes{
    const val MAIN_ROUTE = "main_flow"
    const val MAIN_USER_EDIT = "edit_profile_screen"
}

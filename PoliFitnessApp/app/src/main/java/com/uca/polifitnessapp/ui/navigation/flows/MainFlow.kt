package com.uca.polifitnessapp.ui.navigation.flows

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.uca.polifitnessapp.ui.calculator.ui.CalculatorScreen
import com.uca.polifitnessapp.ui.user.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.user.viewmodel.EditProfileViewModel
import com.uca.polifitnessapp.ui.user.ui.ProfileScreen
import com.uca.polifitnessapp.ui.navigation.PreviewScreens
import com.uca.polifitnessapp.ui.navigation.components.ButtomNavItems
import com.uca.polifitnessapp.ui.news.ui.NewItemBox
import com.uca.polifitnessapp.ui.news.ui.NewItemScreen
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
                newsScreenViewModel,
                navController
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
        composable(MainRoutes.MAIN_CALCULATOR_SCREEN){
            CalculatorScreen()
        }
        // Edit profile route
        composable(MainRoutes.MAIN_USER_EDIT) {
            EditProfileScreen(
                navController,
                userViewModel,
                editProfileViewModel
            )
        }
        // New info route
        composable("new_info_screen/{newId}",
            arguments = listOf(
                navArgument("newId") {
                    type = NavType.StringType
                }
            )
        ) {
            val newId = it.arguments?.getString("newId")

            val new = remember(newId) {
                newId?.let {newId->
                    newsScreenViewModel.fetchNewById(newId)
                }
            }

            when {
                new != null -> NewItemBox(
                    new,
                    navController = navController
                )
                else -> {
                    Toast.makeText(
                        navController.context,
                        "Error al cargar la noticia",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

// ---
// Routes
// ---

object MainRoutes{
    const val MAIN_ROUTE = "main_flow"
    const val MAIN_USER_EDIT = "edit_profile_screen"
    const val MAIN_NEW_INFO = "new_info_screen/{newId}"
    const val MAIN_CALCULATOR_SCREEN = "calculator_screen"
}

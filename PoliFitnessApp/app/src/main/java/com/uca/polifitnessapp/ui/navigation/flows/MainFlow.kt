package com.uca.polifitnessapp.ui.navigation.flows

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.uca.polifitnessapp.ui.main.calculator.ui.CalculatorScreen
import com.uca.polifitnessapp.ui.extras.ui.Contact
import com.uca.polifitnessapp.ui.main.home.ui.Home
import com.uca.polifitnessapp.ui.main.home.viewmodel.HomeScreenViewModel
import com.uca.polifitnessapp.ui.navigation.components.ButtomNavItems
import com.uca.polifitnessapp.ui.main.news.ui.NewItemBox
import com.uca.polifitnessapp.ui.main.news.ui.NewsListScreen
import com.uca.polifitnessapp.ui.main.news.viewmodel.NewsItemViewModel
import com.uca.polifitnessapp.ui.main.news.viewmodel.NewsScreenViewModel
import com.uca.polifitnessapp.ui.extras.ui.privacyPoliticsScreen
import com.uca.polifitnessapp.ui.main.routines.viewmodel.RoutinesViewModel
import com.uca.polifitnessapp.ui.main.routines.ui.RoutineItemScreen
import com.uca.polifitnessapp.ui.main.routines.ui.RoutinesListScreen
import com.uca.polifitnessapp.ui.main.routines.viewmodel.RoutineItemViewModel
import com.uca.polifitnessapp.ui.user.ui.EditApproachScreen
import com.uca.polifitnessapp.ui.user.ui.EditProfileScreen
import com.uca.polifitnessapp.ui.user.ui.ProfileScreen
import com.uca.polifitnessapp.ui.user.viewmodel.EditProfileViewModel
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
    newsItemViewModel: NewsItemViewModel,
    userViewModel: UserViewModel,
    routinesViewModel: RoutinesViewModel,
    routineItemViewModel: RoutineItemViewModel,
    homeScreenViewModel: HomeScreenViewModel
) {
    navigation(
        startDestination = ButtomNavItems.Home.rute,
        route = MainRoutes.MAIN_ROUTE
    ) {
        // ---
        // Home route
        // ---
        composable(
            ButtomNavItems.Home.rute
        ) {
            Home(
                homeScreenViewModel,
                userViewModel,
                onRoutinesClick = {
                    navController.navigate(ButtomNavItems.Rutine.rute) {
                        popUpTo(ButtomNavItems.Home.rute) {
                            inclusive = true
                        }
                    }
                },
                onNewsClick = {
                    navController.navigate(ButtomNavItems.News.rute) {
                        popUpTo(ButtomNavItems.Home.rute) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToNews = { noticeId ->
                    navController.navigate("new_info_screen/${noticeId}")
                },
                onNavigateToRoutine = { routineId ->
                    navController.navigate("routine_info_screen/${routineId}")
                },
                onNavigateToProfile = {
                    navController.navigate(ButtomNavItems.Profile.rute){
                        popUpTo(ButtomNavItems.Home.rute) {
                            inclusive = true
                        }
                    }
                },
                userId = userViewModel.user._id ?: "",
            )
        }
    }
    // ---
    // News route
    // ---
    composable(ButtomNavItems.News.rute) {
        NewsListScreen(
            newsScreenViewModel,
            onNavigateToNews = { noticeId ->
                navController.navigate("new_info_screen/${noticeId}")
            }
        )
    }
    // ---
    // Routine route
    // ---
    composable(ButtomNavItems.Rutine.rute) {
        RoutinesListScreen(
            routinesViewModel,
            userViewModel,
            onNavigateToRoutine = { routineId ->
                navController.navigate("routine_info_screen/${routineId}"){
                }
            }
        )
    }
    // ---
    // Profile route
    // ---
    composable(ButtomNavItems.Profile.rute) {
        ProfileScreen(
            userViewModel,
            userId = userViewModel.user._id ?: "",
            onNavigateToEditProfile = { userId ->
                navController.navigate("edit_profile_screen/${userId}")
            },
            onNavigateToTermsAndConditions = {
                navController.navigate(MainRoutes.MAIN_TERMS_AND_CONDITIONS)
            },
            onNavigateToContactUs = {
                navController.navigate(MainRoutes.MAIN_CONTACT_INFO)
            },
            onLogout = {
                navController.navigate(LoginRoutes.LOGIN_ROUTE) {
                    popUpTo(MainRoutes.MAIN_ROUTE) {
                        inclusive = true
                    }
                }
            }
        )
    }

    // ---
    // Calculator screen
    // ----

    composable(MainRoutes.MAIN_CALCULATOR_SCREEN) {
        CalculatorScreen()
    }

    // ---
    // Edit profile route
    // ---

    composable(
        "edit_profile_screen/{userId}",
        arguments = listOf(
            navArgument("userId") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString("userId")?.let {
            EditProfileScreen(
                userViewModel,
                editProfileViewModel,
                it,
                onBackPress = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.navigate(MainRoutes.MAIN_EDIT_APPROACH)
                }
            )
        }
    }

    composable(
        MainRoutes.MAIN_EDIT_APPROACH
    ){
        EditApproachScreen(
            editProfileViewModel,
            userViewModel,
            onUpdateSuccess = {
                navController.navigate(ButtomNavItems.Profile.rute){
                    popUpTo(MainRoutes.MAIN_ROUTE){
                        inclusive = true
                    }
                }
            },
        )
    }

    // ---
    // New info route
    // ---

    composable(
        "new_info_screen/{noticeId}",
        arguments = listOf(
            navArgument("noticeId") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString("noticeId")?.let {
            NewItemBox(
                newsItemViewModel,
                it,
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }

    // ---
    // Routine info route
    // ---

    composable(
        "routine_info_screen/{routineId}",
        arguments = listOf(
            navArgument("routineId") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString("routineId")?.let {
            RoutineItemScreen(
                routineItemViewModel,
                it,
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }

    // ---
    // Auxiliary routes
    // ---
    composable(
        MainRoutes.MAIN_CONTACT_INFO
    ) {
        Contact(
            onBackPress = {
                navController.popBackStack()
            }
        )
    }
    composable(
        MainRoutes.MAIN_TERMS_AND_CONDITIONS
    ) {
        privacyPoliticsScreen(
            onBackPress = {
                navController.popBackStack()
            }
        )
    }
}


// ---
// Routes
// ---

object MainRoutes {
    const val MAIN_ROUTE = "main_flow"
    const val MAIN_USER_EDIT = "edit_profile_screen"
    const val MAIN_NEW_INFO = "new_info_screen"
    const val MAIN_CALCULATOR_SCREEN = "calculator_screen"
    const val MAIN_CONTACT_INFO = "contact_screen"
    const val MAIN_TERMS_AND_CONDITIONS = "terms_and_conditions_screen"
    const val MAIN_EDIT_APPROACH = "edit_approach_screen"
}

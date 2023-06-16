package com.uca.polifitnessapp.ui.navigation.flows

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.uca.polifitnessapp.ui.loadingscreen.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.onboardscreen.ui.MainFunction
import com.uca.polifitnessapp.ui.signup.approachscreen.ui.SignUpGoalScreen
import com.uca.polifitnessapp.ui.signup.personalinfoscreen.SignUpPersonalInfoScreen
import com.uca.polifitnessapp.ui.signup.signupscreen.SignUpScreen
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpGoalViewModel
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpPersonalInfoViewModel
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel

// ---
// New user flow
// ---

// This flow contains the onboard screen and the register screen
// The register screen is the first screen that the user will see after the onboard screen

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel,
    signUpPersonalViewModel: SignUpPersonalInfoViewModel,
    signUpGoalViewModel: SignUpGoalViewModel,
    userViewModel: UserViewModel
) {
    navigation(
        startDestination = AuthRoutes.SIGN_UP_SCREEN,
        route = AuthRoutes.NEW_USER_FLOW
    ) {
        composable(AuthRoutes.SPLASH_SCREEN) {
            AnimatedSplashScreen(
                navController = navController
            )
        }
        composable(AuthRoutes.ONBOARD_SCREEN) {
            MainFunction(
                navController = navController
            )
        }
        composable(AuthRoutes.SIGN_UP_SCREEN) {
            SignUpScreen(
                navController,
                signUpViewModel,
                userViewModel
            )
        }
        composable(AuthRoutes.PERSONAL_INFO_SCREEN) {
            SignUpPersonalInfoScreen(
                navController,
                signUpPersonalViewModel,
                userViewModel
            )
        }
        composable(AuthRoutes.GOAL_SCREEN) {
            SignUpGoalScreen(
                navController,
                signUpGoalViewModel,
                userViewModel
            )
        }

    }
}

// ---
// Routes
// ---

object AuthRoutes {
    const val NEW_USER_FLOW = "new_user_flow"
    const val SPLASH_SCREEN = "splash_screen"
    const val ONBOARD_SCREEN = "onboard_screen"
    const val SIGN_UP_SCREEN = "sign_up_screen"
    const val PERSONAL_INFO_SCREEN = "personal_info_screen"
    const val GOAL_SCREEN = "goal_screen"
}

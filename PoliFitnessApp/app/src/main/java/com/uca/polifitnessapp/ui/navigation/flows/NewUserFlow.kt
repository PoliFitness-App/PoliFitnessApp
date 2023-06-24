package com.uca.polifitnessapp.ui.navigation.flows

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.uca.polifitnessapp.ui.extras.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.auth.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.auth.login.viewmodel.LoginViewModel
import com.uca.polifitnessapp.ui.auth.onBoard.ui.MainFunction
import com.uca.polifitnessapp.ui.auth.signup.ui.SignUpGoalScreen
import com.uca.polifitnessapp.ui.auth.signup.ui.SignUpPersonalInfoScreen
import com.uca.polifitnessapp.ui.auth.signup.ui.SignUpScreen
import com.uca.polifitnessapp.ui.auth.signup.viewmodel.SignUpGoalViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel

// ---
// New user flow
// ---

// This flow contains the onboard screen and the register screen
// The register screen is the first screen that the user will see after the onboard screen

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    signUpGoalViewModel: SignUpGoalViewModel,
    userViewModel: UserViewModel,
    loginViewModel: LoginViewModel
) {
    navigation(
        startDestination = AuthRoutes.ONBOARD_SCREEN,
        route = AuthRoutes.AUTH_ROUTE
    ) {
        composable(AuthRoutes.SPLASH_SCREEN) {
            AnimatedSplashScreen(
                navController = navController
            )
        }
        composable(AuthRoutes.ONBOARD_SCREEN) {
            MainFunction(
                onNavigateToAuth = {
                    navController.popBackStack()
                    navController.navigate(AuthRoutes.SIGN_UP_SCREEN)
                },
            )
        }
        composable(AuthRoutes.SIGN_UP_SCREEN) {
            SignUpScreen(
                signUpGoalViewModel,
                onLoginOption = {
                    navController.navigate(AuthRoutes.LOGIN_SCREEN)
                },
                onSignUpSuccess = {
                    navController.popBackStack()
                    navController.navigate(AuthRoutes.PERSONAL_INFO_SCREEN)
                }
            )
        }
        composable(AuthRoutes.PERSONAL_INFO_SCREEN) {
            SignUpPersonalInfoScreen(
                signUpGoalViewModel,
                onSignUpSuccess = {
                    navController.navigate(AuthRoutes.GOAL_SCREEN)
                }
            )
        }
        composable(AuthRoutes.GOAL_SCREEN) {
            SignUpGoalScreen(
                signUpGoalViewModel,
                onSignUpSuccess = {
                    navController.popBackStack()
                    navController.navigate(LoginRoutes.LOGIN_SCREEN)
                }
            )
        }
        composable(AuthRoutes.LOGIN_SCREEN) {
            LoginScreen(
                viewModel = loginViewModel,
                userViewModel = userViewModel,
                onLoginSuccess = {
                    navController.popBackStack()
                    navController.navigate(MainRoutes.MAIN_ROUTE) {
                        popUpTo(AuthRoutes.AUTH_ROUTE) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToSignUp = {
                    navController.popBackStack()
                    navController.navigate(AuthRoutes.SIGN_UP_SCREEN)
                }
            )
        }
    }
}

// ---
// Routes
// ---

object AuthRoutes {
    const val AUTH_ROUTE = "new_user_flow"
    const val SPLASH_SCREEN = "splash_screen"
    const val ONBOARD_SCREEN = "onboard_screen"
    const val SIGN_UP_SCREEN = "sign_up_screen"
    const val PERSONAL_INFO_SCREEN = "personal_info_screen"
    const val GOAL_SCREEN = "goal_screen"
    const val LOGIN_SCREEN = "login_screen"
}

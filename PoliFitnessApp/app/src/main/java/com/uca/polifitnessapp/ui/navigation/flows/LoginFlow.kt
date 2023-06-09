package com.uca.polifitnessapp.ui.navigation.flows

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.uca.polifitnessapp.ui.extras.ui.AnimatedSplashScreen
import com.uca.polifitnessapp.ui.auth.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.auth.login.viewmodel.LoginViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel

// ---
// Login flow
// ---

// This flow is for users that are already registered
// The user will be redirected to this flow if the user is already logged in

fun NavGraphBuilder.loginGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    userViewModel: UserViewModel
){
    navigation(
        startDestination = LoginRoutes.LOGIN_SPLASH_SCREEN,
        route = LoginRoutes.LOGIN_ROUTE
    ) {

        // --
        // Splash screen
        // --
        composable(LoginRoutes.LOGIN_SPLASH_SCREEN) {
            AnimatedSplashScreen(
                onNavigate = {
                    navController.popBackStack()
                    navController.navigate(LoginRoutes.LOGIN_SCREEN)
                }
            )
        }
        // --
        // Login screen
        // --
        composable(LoginRoutes.LOGIN_SCREEN) {
            LoginScreen(
                viewModel = loginViewModel,
                userViewModel = userViewModel,
                onLoginSuccess = {
                    navController.popBackStack()
                    navController.navigate(MainRoutes.MAIN_ROUTE){
                        popUpTo(AuthRoutes.AUTH_ROUTE){
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

object LoginRoutes{
    const val LOGIN_ROUTE = "auth_flow"
    const val LOGIN_SPLASH_SCREEN = "splash_screen"
    const val LOGIN_SCREEN = "login_screen"
}

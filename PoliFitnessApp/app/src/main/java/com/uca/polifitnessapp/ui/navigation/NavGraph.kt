package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.ui.auth.login.state.UserState
import com.uca.polifitnessapp.ui.main.home.viewmodel.HomeScreenViewModel
import com.uca.polifitnessapp.ui.auth.login.viewmodel.LoginViewModel
import com.uca.polifitnessapp.ui.navigation.components.ButtomNavItems.*
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.navigation.flows.MainRoutes
import com.uca.polifitnessapp.ui.navigation.flows.authGraph
import com.uca.polifitnessapp.ui.navigation.flows.loginGraph
import com.uca.polifitnessapp.ui.navigation.flows.mainGraph
import com.uca.polifitnessapp.ui.main.news.viewmodel.NewsItemViewModel
import com.uca.polifitnessapp.ui.main.news.viewmodel.NewsScreenViewModel
import com.uca.polifitnessapp.ui.main.routines.viewmodel.RoutinesViewModel
import com.uca.polifitnessapp.ui.main.routines.viewmodel.RoutineItemViewModel
import com.uca.polifitnessapp.ui.auth.signup.viewmodel.SignUpGoalViewModel
import com.uca.polifitnessapp.ui.navigation.flows.LoginRoutes
import com.uca.polifitnessapp.ui.user.viewmodel.EditProfileViewModel
import com.uca.polifitnessapp.ui.user.viewmodel.UserViewModel

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

    val newsItemViewModel: NewsItemViewModel = viewModel(
        factory = NewsItemViewModel.Factory
    )

    // User view model (global)
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModel.Factory
    )

    // Edit profile view model
    val editProfileViewModel: EditProfileViewModel = viewModel(
        factory = EditProfileViewModel.Factory
    )

    // Routines view model
    val routinesViewModel: RoutinesViewModel = viewModel(
        factory = RoutinesViewModel.Factory
    )

    // Routine Item ViewModel
    val routineItemViewModel: RoutineItemViewModel = viewModel(
        factory = RoutineItemViewModel.Factory
    )

    // Home screen ViewModel
    val homeScreenViewModel: HomeScreenViewModel = viewModel(
        factory = HomeScreenViewModel.Factory
    )

    // ---
    // Sign up view models
    // ---

    // Sign up goal view model
    val signUpGoalViewModel: SignUpGoalViewModel = viewModel(
        factory = SignUpGoalViewModel.Factory
    )

    // Application Instance
    val app: PoliFitnessApplication = LocalContext.current.applicationContext
            as PoliFitnessApplication

    // ---
    // Nav Host
    // ---

    // First thing to do is to get the user info, we use launched effect to do this
    // This will be executed only once when the app is launched
    LaunchedEffect(Unit) {
        try {
            // set retrofit token
            app.setRetrofitToken()
            // get user info
            userViewModel.getUserInfo()
            userViewModel.getUser()
        }catch (e: Exception) {
            app.saveUserState(UserState.LOGGED_OUT)
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (app.getUserState() == UserState.LOGGED_IN) {
            MainRoutes.MAIN_ROUTE
        } else if (app.getUserState() == UserState.NEW_USER) {
            AuthRoutes.AUTH_ROUTE
        } else {
            LoginRoutes.LOGIN_ROUTE
        }
    ) {
        // ---
        // Main flow
        // ---

        mainGraph(
            navController,
            editProfileViewModel,
            newsScreenViewModel,
            newsItemViewModel,
            userViewModel,
            routinesViewModel,
            routineItemViewModel,
            homeScreenViewModel
        )

        // ---
        // New user flow
        // ---

        authGraph(
            navController,
            signUpGoalViewModel,
            userViewModel,
            loginViewModel
        )

        // ---
        // Login flow
        // ---

        loginGraph(
            navController,
            loginViewModel,
            userViewModel
        )
    }
}

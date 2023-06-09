package com.uca.polifitnessapp.ui.navigation.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.ui.navigation.NavigationHost
import com.uca.polifitnessapp.ui.navigation.flows.AuthRoutes
import com.uca.polifitnessapp.ui.navigation.flows.MainRoutes

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScalfold() {

    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

    // Bottom bar state
    when (navBackStackEntry.value?.destination?.route) {
        // New user flow
        "new_user_flow" -> {
            bottomBarState.value = false
        }

        // Onboarding flow
        "onboard_screen" -> {
            bottomBarState.value = false
        }
        "splash_screen" -> {
            bottomBarState.value = false
        }

        // Login screen
        "login_screen" -> {
            bottomBarState.value = false
        }

        // Register screen
        AuthRoutes.SIGN_UP_SCREEN -> {
            bottomBarState.value = false
        }

        AuthRoutes.PERSONAL_INFO_SCREEN ->{
            bottomBarState.value = false
        }

        AuthRoutes.GOAL_SCREEN ->{
            bottomBarState.value = false
        }

        MainRoutes.MAIN_NEW_INFO ->{
            bottomBarState.value = false
        }

        MainRoutes.MAIN_EDIT_APPROACH ->{
            bottomBarState.value = false
        }

        // Edit profile
        "edit_profile_screen/{userId}" -> {
            bottomBarState.value = false
        }

        "routine_info_screen/{routineId}" ->{
            bottomBarState.value = false
        }

        "new_info_screen/{noticeId}" ->{
            bottomBarState.value = false
        }

        MainRoutes.MAIN_CONTACT_INFO ->{
            bottomBarState.value = false
        }

        MainRoutes.MAIN_TERMS_AND_CONDITIONS ->{
            bottomBarState.value = false
        }

        // default
        else -> {
            bottomBarState.value = true
        }
    }

    // Scaffold
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                BottomBar(
                    navController = navController,
                    bottomBarState
                )
            }
        }
    ) {
        Box() {
            // Navigation host
            NavigationHost(navController = navController)
        }
    }
}
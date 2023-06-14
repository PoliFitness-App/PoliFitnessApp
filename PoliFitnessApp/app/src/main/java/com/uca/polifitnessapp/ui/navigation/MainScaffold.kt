package com.uca.polifitnessapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScalfold() {

    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

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
        "register_screen" -> {
            bottomBarState.value = false
        }

        // Edit profile
        "edit_profile_screen" -> {
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
            BottomBar(
                navController = navController,
                bottomBarState
            )
        }
    ) {
        Box() {
            // Navigation host
            NavigationHost(navController = navController)
        }
    }
}
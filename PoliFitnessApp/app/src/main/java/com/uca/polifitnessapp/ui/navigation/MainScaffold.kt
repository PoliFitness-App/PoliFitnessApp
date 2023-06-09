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
        // Main flow
        "main_flow" -> {
            bottomBarState.value = true
        }
        // Auth flow
        "auth_flow" -> {
            bottomBarState.value = false
        }
        // New user flow
        "new_user_flow" -> {
            bottomBarState.value = false
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
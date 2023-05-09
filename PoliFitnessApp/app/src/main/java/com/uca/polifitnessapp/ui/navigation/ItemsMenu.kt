package com.uca.polifitnessapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsMenu(
    val icon: ImageVector,
    val title: String,
    val rute: String
) {

    object Home : ItemsMenu(
        icon = Icons.Outlined.Home,
        title = "Home",
        rute = "home"
    )
    object News: ItemsMenu(
        icon = Icons.Outlined.Apps,
        title = "News",
        rute = "news"
    )
    object Rutine : ItemsMenu(
        icon = Icons.Outlined.FitnessCenter,
        title = "Rutine",
        rute = "rutine"
    )
    object Profile: ItemsMenu(
        icon = Icons.Outlined.Person,
        title = "Profile",
        rute = "profile"
    )
}
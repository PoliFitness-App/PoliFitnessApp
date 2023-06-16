package com.uca.polifitnessapp.ui.navigation.components

import com.uca.polifitnessapp.R

sealed class ButtomNavItems(
    val icon: Int,
    val title: String,
    val rute: String,
    val iconFocus : Int
) {
    object Home : ButtomNavItems(
        icon = R.drawable.homeicon,
        title = "Home",
        rute = "home",
        iconFocus = R.drawable.homefocus
    )
    object News: ButtomNavItems(
        icon = R.drawable.newsicon,
        title = "News",
        rute = "news",
        iconFocus = R.drawable.newsfocus
    )
    object Rutine : ButtomNavItems(
        icon = R.drawable.rutinesicon,
        title = "Rutine",
        rute = "rutine",
        iconFocus = R.drawable.rutinesfocus
    )
    object Profile: ButtomNavItems(
        icon = R.drawable.profileicon,
        title = "Profile",
        rute = "profile",
        iconFocus = R.drawable.profilefocus
    )
}
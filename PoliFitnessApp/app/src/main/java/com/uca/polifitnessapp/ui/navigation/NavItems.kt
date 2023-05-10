package com.uca.polifitnessapp.ui.navigation

import com.uca.polifitnessapp.R

sealed class NavItems(
    val icon: Int,
    val title: String,
    val rute: String,
    val iconFocus : Int
) {

    object Home : NavItems(
        icon = R.drawable.homeicon,
        title = "Home",
        rute = "home",
        iconFocus = R.drawable.homefocus
    )
    object News: NavItems(
        icon = R.drawable.newsicon,
        title = "News",
        rute = "news",
        iconFocus = R.drawable.newsfocus
    )
    object Rutine : NavItems(
        icon = R.drawable.rutinesicon,
        title = "Rutine",
        rute = "rutine",
        iconFocus = R.drawable.rutinesfocus
    )
    object Profile: NavItems(
        icon = R.drawable.profileicon,
        title = "Profile",
        rute = "profile",
        iconFocus = R.drawable.profilefocus
    )
}
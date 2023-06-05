package com.uca.polifitnessapp.ui.navigation

sealed class UserScreens(val route: String){
    object UserProfileScreen: UserScreens("user_profile_screen")
    object EditProfileScreen: UserScreens("edit_profile_screen")
}

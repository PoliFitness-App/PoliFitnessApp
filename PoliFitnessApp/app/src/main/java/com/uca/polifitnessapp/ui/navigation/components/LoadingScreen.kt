package com.uca.polifitnessapp.ui.navigation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp)
    )
}
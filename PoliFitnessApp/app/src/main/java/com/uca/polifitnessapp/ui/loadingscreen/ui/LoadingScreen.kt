package com.uca.polifitnessapp.ui.LoadingScreen.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uca.polifitnessapp.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = repeatable(
            iterations = 3,
            animation = tween(durationMillis = 2000,
            easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate("login_screen")
    }

    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float){
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.uca_logo) ,
            modifier = Modifier
                .width(150.dp)
                .height(100.dp)
                .alpha(alpha = alpha),
            contentDescription = "UCA LOGO"
        )
    }
}
package com.uca.polifitnessapp.ui.signup.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.loadingscreen.onboardscreen.data.OnboardData
import com.uca.polifitnessapp.ui.loadingscreen.onboardscreen.ui.OnBoardingPager


@Composable
fun SignUpGoalScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignUpGoalText()
        CarouselCard()
        ConfirmGoalButton(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun SignUpGoalText(
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "¿Cuál es tu meta?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)

        Text(text = "Nos ayudará a elegir el mejor programa para ti.",
            textAlign = TextAlign.Center
        )


    }
}

@Composable
fun CardGoalImage(){
    Image(painter = painterResource(id = R.drawable.cardgoal1),
        contentDescription = null,
        modifier = Modifier
            .size(430.dp)
    )
}

@Preview(showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(){
    val pagerState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f
    ) {
        2
    }

    Column(
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 65.dp),
            modifier = Modifier.height(350.dp),
            verticalAlignment = Alignment.CenterVertically,


        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .graphicsLayer {
                        //val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    }
            ){
                Image(painter = painterResource(id = R.drawable.cardgoal1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(600.dp)
                )

            }
            
        }

    }
}
@Composable
fun ConfirmGoalButton(modifier: Modifier) {
    Button(
        onClick = { "/*TODO*/ "},
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .width(315.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF034189)
        ),

        )
    {
        Text(
            text = "Confirmar",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
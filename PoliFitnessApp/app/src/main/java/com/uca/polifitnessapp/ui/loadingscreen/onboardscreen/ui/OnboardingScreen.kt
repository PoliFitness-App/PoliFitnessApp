@file:OptIn(ExperimentalFoundationApi::class)

package com.uca.polifitnessapp.ui.loadingscreen.onboardscreen.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.loadingscreen.onboardscreen.data.OnboardData
import kotlinx.coroutines.launch

@Composable
fun MainFunction(navController: NavController) {
    //  Create a List of items ( on board screens)
    val items = arrayListOf<OnboardData>()

    // item = { image, tittle, desc, button }

    //  Add items to the list

    // Add item 1
    items.add(
        OnboardData(
            image = R.drawable.screen1,
            tittle = "Sigue tu meta",
            desc = "No te preocupes si tienes dificultad en recordar por cual parte de la rutina te quedaste, nosotros hacemos ese trabajo por ti!",
            button = R.drawable.button1
        )
    )

    // Add item 2
    items.add(
        OnboardData(
            image = R.drawable.screen5,
            tittle = "Quema calorias",
            desc = "Vamos a seguir ardiendo, para alcanzar tus metas, duele sólo temporalmente, si te rindes ahora estarás en el dolor para siempre.",
            button = R.drawable.button2
        )
    )

    // Add item 3
    items.add(
        OnboardData(
            image = R.drawable.screen4,
            tittle = "Aliméntate bien",
            desc = "Comencemos un estilo de vida saludable con nosotros, podemos determinar su dieta todos los días. comer sano es divertido.",
            button = R.drawable.button3
        )
    )

    // Add item 4
    items.add(
        OnboardData(
            image = R.drawable.screen2,
            tittle = "Mejorar la calidad del sueño",
            desc = "Un sueño de buena calidad puede traer un buen estado de ánimo por la mañana.",
            button = R.drawable.button4
        )
    )

    // pager state to handle page changes
    val pagerState = rememberPagerState(
        initialPage = 0,
    ) { items.size }

    // Call the OnBoardingPager composable function
    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        navController = navController
    )
}

@Composable
fun OnBoardingPager(
    item: List<OnboardData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // Screen
    Box(modifier = modifier) {
        // Colum
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // Horizonal Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    //Image
                    Image(
                        painterResource(id = item[page].image),
                        contentDescription = "Onboard Image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(16.dp))
                    //Tittle
                    Text(
                        text = item[page].tittle,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(32.dp, 0.dp, 48.dp, 0.dp)
                    )

                    Spacer(modifier = Modifier.padding(16.dp))
                    //Description
                    Text(
                        text = item[page].desc,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(32.dp, 0.dp, 48.dp, 0.dp),
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    // Next page button
                    NextPageButton(
                        modifier = Modifier.align(Alignment.End),
                        page=page,
                        item = item,
                        pagerState = pagerState,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun NextPageButton(
    modifier: Modifier = Modifier,
    page:Int,
    item: List<OnboardData>,
    pagerState: PagerState,
    navController: NavController
) {
    // coroutineScope
    val coroutineScope = rememberCoroutineScope()

    // Next page button
    FloatingActionButton(
        onClick = {
            coroutineScope.launch {
                if (pagerState.currentPage == item.size - 1) {
                    navController.popBackStack()
                    navController.navigate("main_flow")
                } else {
                    // Call scroll to on pagerState
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        },
        modifier = modifier
            .padding(32.dp)
            .height(60.dp)
            .width(60.dp),
        contentColor = Color.Transparent,
        containerColor = Color.Transparent,
        shape = RoundedCornerShape(60.dp),
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
    ) {
        // Next page button image
        Image(
            painterResource(id = item[page].button),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit,
            contentDescription = "Next page"
        )
    }
}
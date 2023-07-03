@file:OptIn(ExperimentalFoundationApi::class)

package com.uca.polifitnessapp.ui.auth.onBoard.ui

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
import androidx.compose.runtime.key
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
import com.uca.polifitnessapp.ui.auth.onBoard.data.OnboardData
import kotlinx.coroutines.launch

@Composable
fun MainFunction(
    onNavigateToAuth: () -> Unit
) {
    //  Create a List of items ( on board screens)
    val items = arrayListOf<OnboardData>()

    // item = { image, tittle, desc, button }

    //  Add items to the list

    // Add item 1
    items.add(
        OnboardData(
            image = R.drawable.on_board_screen_4,
            tittle = "Sigue tu meta",
            desc = "No te preocupes si tienes dificultad en recordar por cual parte de la rutina te quedaste, nosotros hacemos ese trabajo por ti!",
            button = R.drawable.on_board_screen_button_1
        )
    )

    // Add item 2
    items.add(
        OnboardData(
            image = R.drawable.on_board_screen_3,
            tittle = "Quema calorias",
            desc = "Vamos a seguir ardiendo, para alcanzar tus metas, duele sólo temporalmente, si te rindes ahora estarás en el dolor para siempre.",
            button = R.drawable.on_board_screen_button_2
        )
    )

    // Add item 3
    items.add(
        OnboardData(
            image = R.drawable.on_board_screen_2,
            tittle = "Aliméntate bien",
            desc = "Comencemos un estilo de vida saludable con nosotros, podemos determinar su dieta todos los días. comer sano es divertido.",
            button = R.drawable.on_board_screen_button_3
        )
    )

    // Add item 4
    items.add(
        OnboardData(
            image = R.drawable.on_board_screen_1,
            tittle = "Mejorar la calidad del sueño",
            desc = "Un sueño de buena calidad puede traer un buen estado de ánimo por la mañana.",
            button = R.drawable.on_board_screen_button_4
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
        onNavigateToAuth
    )
}

@Composable
fun OnBoardingPager(
    item: List<OnboardData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onNavigateToAuth: () -> Unit
) {
    // Screen
    Box(
        modifier = modifier
    ) {
        // Horizonal Pager
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(0.dp),
            verticalAlignment = Alignment.Top,
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                //Image
                Image(
                    painterResource(id = item[page].image),
                    contentDescription = "Onboard Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                //Tittle
                Text(
                    text = item[page].tittle,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(32.dp, 0.dp, 32.dp, 0.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))
                //Description
                Text(
                    text = item[page].desc,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .height(100.dp)
                        .padding(32.dp, 0.dp, 48.dp, 0.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                // Next page button
                NextPageButton(
                    modifier = Modifier.align(Alignment.End),
                    page = page,
                    item = item,
                    pagerState = pagerState,
                    onNavigateToAuth
                )
            }
        }
    }
}

@Composable
fun NextPageButton(
    modifier: Modifier = Modifier,
    page: Int,
    item: List<OnboardData>,
    pagerState: PagerState,
    onNavigateToAuth: () -> Unit
) {
    // coroutineScope
    val coroutineScope = rememberCoroutineScope()

    // Next page button
    FloatingActionButton(
        onClick = {
            coroutineScope.launch {
                if (pagerState.currentPage == item.size - 1) {
                    // Navigate to Auth screen
                    onNavigateToAuth()
                } else {
                    // Call scroll to on pagerState
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        },
        contentColor = Color.Transparent,
        containerColor = Color.Transparent,
        shape = RoundedCornerShape(60.dp),
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
        modifier = modifier
            .height(80.dp)
            .width(80.dp)
            .padding(0.dp, 0.dp, 32.dp, 32.dp)
    ) {
        // Next page button image
        Image(
            painterResource(id = item[page].button),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight,
            contentDescription = "Next page"
        )
    }
}
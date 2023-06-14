package com.uca.polifitnessapp.ui.signup.approachscreen.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.signup.approachscreen.data.ApproachData


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainFunction(){
    val items = arrayListOf<ApproachData>()

    items.add(
        ApproachData(
            image = R.drawable.approachimg1,
            tittle = "Ganar masa muscular",
            desc = "Tengo poca grasa corporal y quiero desarrollar más músculo.",
        )
    )

    items.add(
        ApproachData(
            image = R.drawable.approachimg2,
            tittle = "Perder grasa",
            desc = "Tengo que perder más de 9 kilos. Quiero bajar toda esta grasa y ganar masa muscular.",
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
    ) { items.size}

    CarouselCard(
        item = items,
        pagerState = pagerState,
    )
}


@Preview(showSystemUi = true)
@Composable
fun SignUpGoalScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignUpGoalText()
        MainFunction()
        ConfirmGoalButton(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}




@Composable
fun SignUpGoalText(
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "¿Cuál es tu meta?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Nos ayudará a elegir el mejor programa para ti.",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(200.dp),
        )


    }
}



//@Preview(showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(
    item: List<ApproachData>,
    pagerState: PagerState,
){
    Column(
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier.height(478.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {page ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(22.dp))
                    .background(
                        colorResource(id = R.color.white)
                    )
                    .padding(10.dp)
                    .width(375.dp)
                    .height(578.dp)

            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(22.dp))
                        .background(
                            colorResource(id = R.color.PrimaryColor)
                        )
                        .padding(10.dp)
                        .width(275.dp)
                        .height(478.dp)
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)

                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Image(
                            painterResource(id = item[page].image),
                            contentDescription = null,
                            modifier = Modifier
                                .height(290.dp)
                                .width(183.dp)

                        )
                        Text(
                            text = item[page].tittle,
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold

                        )
                        Divider(
                            color = Color.White,
                            modifier = Modifier.width(50.dp),
                            thickness = 1.dp
                        )

                        Text(
                            text = item[page].desc,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light

                        )

                    }
                }

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
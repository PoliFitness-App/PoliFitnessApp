package com.uca.polifitnessapp.ui.signup.approachscreen.ui

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.ui.login.ui.LoginUiStatus
import com.uca.polifitnessapp.ui.signup.approachscreen.data.ApproachData
import com.uca.polifitnessapp.ui.signup.validation.SignUpUiStatus
import com.uca.polifitnessapp.ui.signup.viewmodel.SignUpGoalViewModel
import com.uca.polifitnessapp.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainFunction(
    onApproachChange: (String) -> Unit,
) {
    val items = arrayListOf<ApproachData>()

    // ---
    // Items for the carousel (Approach)
    // ---

    // Item 1
    items.add(
        ApproachData(
            image = R.drawable.approachimg1,
            tittle = "Ganar masa muscular",
            desc = "Tengo poca grasa corporal y quiero desarrollar más músculo.",
        )
    )

    // Item 2
    items.add(
        ApproachData(
            image = R.drawable.approachimg2,
            tittle = "Perder grasa",
            desc = "Tengo que perder más de 9 kilos. Quiero bajar toda esta grasa y ganar masa muscular.",
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
    ) { items.size }

    CarouselCard(
        item = items,
        pagerState = pagerState,
    )

    // Current page
    val currentPage = pagerState.currentPage

    // Validation to set te approach
    if (currentPage == 0) {
        onApproachChange("Ganar masa muscular")
    } else {
        onApproachChange("Perder grasa")
    }
}

@Composable
fun SignUpGoalScreen(
    navController: NavController,
    viewModel: SignUpGoalViewModel,
    userViewModel: UserViewModel
) {

    // ---
    // Variables
    // ---
    // Is wrong ?
    val isEnabled: Boolean by viewModel.isEnabled.observeAsState(initial = false)
    // Approach
    val approach: String by viewModel.approach.observeAsState(initial = "")

    // ---
    // Auxiliary variables
    // ---
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // ---
    // viewModel
    // ---
    // Get the user from the view model
    val user = userViewModel.user.value!!
    val password = userViewModel.password.value!!

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
        // ---
        // Tittle
        // ---
        SignUpGoalText()
        // ---
        // Carousel
        // ---
        MainFunction(){
            // Set the approach on the view model
            viewModel.onApproachChange(it)

            // Update the approach on the user
            user.approach = it
        }
        // ---
        // Button
        // ---
        ConfirmGoalButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            confirmGoal = isEnabled,
        ) {
            coroutineScope.launch {
                viewModel.status.observe(lifecycleOwner) { status ->
                    handleUiStatus(
                        status,
                        navController,
                        context,
                        viewModel,
                        user,
                        password
                    )
                }
            }
        }
    }
}

// ---
// Handle status changes
//
fun handleUiStatus(
    status: SignUpUiStatus,
    navController: NavController,
    context: Context,
    viewModel: SignUpGoalViewModel,
    user: UserModel,
    password: String
) {
    when (status) {
        is SignUpUiStatus.Error -> {
            Toast.makeText(context, "Error de conexion", Toast.LENGTH_SHORT).show()
        }

        is SignUpUiStatus.ErrorWithMessage -> {
            Toast.makeText(context, status.message, Toast.LENGTH_SHORT).show()
        }

        is SignUpUiStatus.Success -> {

            // TODO: Save user data
            viewModel.onSignUp(user,password)

            // We nagivate to "main_flow"
            navController.navigate("main_flow")
        }

        else -> {}
    }
}

@Composable
fun SignUpGoalText(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¿Cuál es tu meta?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Nos ayudará a elegir el mejor programa para ti.",
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
) {
    Column(
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 30.dp),
            modifier = Modifier.height(478.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->
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
                ) {
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
fun ConfirmGoalButton(
    modifier: Modifier,
    confirmGoal: Boolean,
    onConfirm: () -> Unit
) {
    Button(
        onClick = { onConfirm() },
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
        enabled = confirmGoal
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
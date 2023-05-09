@file:OptIn(ExperimentalMaterial3Api::class)

package com.uca.polifitnessapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uca.polifitnessapp.ui.login.ui.LoginScreen
import com.uca.polifitnessapp.ui.login.ui.LoginViewModel
import com.uca.polifitnessapp.ui.navigation.ItemsMenu
import com.uca.polifitnessapp.ui.navigation.NavigationHost
import com.uca.polifitnessapp.ui.theme.PoliFitnessAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PoliFitnessAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // MANDAMOS A LLAMAR EL LOGIN SCREEN
                    //MainScreen()
                    LoginScreen(LoginViewModel())

                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navigation_item = listOf(
        ItemsMenu.Home,
        ItemsMenu.News,
        ItemsMenu.Rutine,
        ItemsMenu.Profile
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(navController, navigation_item)
        },
        floatingActionButton = {
            fab(scope, scaffoldState)
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {

        LoginScreen(LoginViewModel())

        NavigationHost(navController)
        //MainScreenNavigationConfigurations(navController)
    }

}

@Composable
fun fab(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    FloatingActionButton(
        onClick = {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    "Hello from Snackbar",
                    actionLabel = "Aceptar",
                    duration = SnackbarDuration.Short
                )
            }
        },
        containerColor = Color(0xFF034189),
        contentColor = Color.White,
        shape = RoundedCornerShape(50.dp)
    ) {
        Icon(imageVector = Icons.Outlined.Calculate, contentDescription = "")
    }
}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    menu_items: List<ItemsMenu>
) {

    // BottomNavigation

    BottomAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        cutoutShape = MaterialTheme.shapes.large
    ) {


        val currentRoute = currentRoute(navController)

        menu_items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier
                            .weight(2f, true)
                    )
                },
                selected = currentRoute == item.rute,
                onClick = {
                    navController.navigate(item.rute)
                },
                modifier = Modifier
                    .padding(8.dp),
            )
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {

    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route

}

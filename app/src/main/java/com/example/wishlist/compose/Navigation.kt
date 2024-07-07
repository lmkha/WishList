package com.example.wishlist.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeView(
                navHostController = navHostController
            )
        }

        composable(route = Screen.AddScreen.route) {
            Log.i("CHECK_VAR", "Navigation, add")
            AddEditDetailView(
                id = 0L,
                navHostController = navHostController
            )
        }

        composable(
            route = Screen.EditScreen.route + "/{wishId}",
            arguments = listOf( navArgument("wishId") { type = NavType.LongType } )
        ) { navBackStackEntry ->
            val wishId = navBackStackEntry.arguments?.getLong("wishId")
            wishId?.let {
                AddEditDetailView(
                    id = it,
                    navHostController = navHostController
                )
            }
        }
    }
}



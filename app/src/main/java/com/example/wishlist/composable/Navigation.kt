package com.example.wishlist.composable

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wishlist.Screen
import com.example.wishlist.WishViewModel

@Composable
fun Navigation(viewModel: WishViewModel) {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeView(
                viewModel = viewModel,
                navHostController = navHostController
            )
        }

        composable(route = Screen.AddScreen.route) {
            Log.i("CHECK_VAR", "Navigation, add")
            AddEditDetailView(
                id = 0L,
                viewModel = viewModel,
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
                    viewModel = viewModel,
                    navHostController = navHostController
                )
            }
        }
    }
}



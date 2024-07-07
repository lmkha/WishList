package com.example.wishlist.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.wishlist.viewmodels.HomeViewModel
import com.example.wishlist.ui.theme.Pink80

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBarView(title = "Home Screen", isHomeScreen = true)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.AddScreen.route)
                },
                contentColor = Color.White,
                containerColor = Color.Black,
                shape = CircleShape,
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {scaffoldPaddingValues ->
        val wishList = viewModel.getAllWishes().collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPaddingValues)
                .background(color = Pink80)
        ) {
            items(
                items = wishList.value,
                key = { wish -> wish.id }
            ) { wish ->
                WishItem(
                    wish = wish,
                    onLongClick = {
                        Toast
                            .makeText(context, "Long Clicked on ${wish.title}", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onSwipeEndToStart = {
                        viewModel.deleteWish(wish)
                    },
                    onSwipeStartToEnd = {
                        navHostController.navigate("${Screen.EditScreen.route}/${wish.id}")
                    },
                    onClick = {
                        navHostController.navigate("${Screen.EditScreen.route}/${wish.id}")
                    }
                )
            }
        }
    }
}

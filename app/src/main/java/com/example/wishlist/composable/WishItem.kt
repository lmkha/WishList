package com.example.wishlist.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wishlist.data.Wish

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WishItem(
    wish: Wish,
    onSwipeStartToEnd: () -> Unit = {},
    onSwipeEndToStart: () -> Unit = {},
    onLongClick: () -> Unit = {},
    onClick: () -> Unit
) {
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            when (state) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onSwipeStartToEnd()
                    false
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    onSwipeEndToStart()
                    true
                }

                else -> false
            }
        },
        positionalThreshold = {
            it * 0.25f
        }
    )

    SwipeToDismissBox(
        state = state,
        backgroundContent = {
            val alignment: Alignment
            val color: Color
            val icon: @Composable () -> Unit

            when (state.targetValue) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    alignment = Alignment.CenterStart
                    color = Color.Green
                    icon = { Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit wish") }
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    alignment = Alignment.CenterEnd
                    color = Color.Red
                    icon = { Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete wish") }
                }
                else -> {
                    alignment = Alignment.Center
                    color = Color.Transparent
                    icon = {}
                }
            }

            Box(
                modifier = Modifier
                    .background(color = color)
                    .fillMaxSize()
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = alignment
            ) {
                icon()
            }
        },
        enableDismissFromEndToStart = true,
        enableDismissFromStartToEnd = true
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp,
                )
                .clip(RoundedCornerShape(16.dp))
                .combinedClickable(
                    onClick = { onClick() },
                    onLongClick = { onLongClick() }
                ),
            colors = CardDefaults.cardColors(
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(16.dp)

        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = wish.title,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = wish.description
                )
            }
        }
    }
}
package com.example.myfitness.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.myfitness.Page

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    handleChangePage: (page: Page) -> Unit,
    handleDisconnect: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "My Fitness") },
        actions = {
            IconButton(onClick = { handleChangePage(Page.WORKOUTS) }) {
                Icon(
                    Icons.Rounded.List,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { handleChangePage(Page.COLANDER) }) {
                Icon(
                    Icons.Rounded.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(handleDisconnect) {
                Icon(
                    Icons.Rounded.ExitToApp,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
    )
}
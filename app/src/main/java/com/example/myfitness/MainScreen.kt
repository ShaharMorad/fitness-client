package com.example.myfitness

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyScreen(currentPages: Pages) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Top Bar Title") },
                actions = {
                    // Add your icon buttons here
                    IconButton(onClick = { /* Handle first icon click */ }) {
                        Icon(
                            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                            contentDescription = "First Icon",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                    IconButton(onClick = { /* Handle first icon click */ }) {
                        Icon(
                            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                            contentDescription = "First Icon",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                    IconButton(onClick = { /* Handle first icon click */ }) {
                        Icon(
                            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                            contentDescription = "First Icon",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
        },
        content = { Text(text = "hi")}
    )
}
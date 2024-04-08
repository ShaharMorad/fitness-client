package com.example.myfitness.worktousScreens.workoutCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfitness.utils.formatDate
import com.example.myfitness.worktousScreens.WorkoutModel
import com.example.myfitness.worktousScreens.workoutsListScreen.WorkoutsListView
import java.util.Date
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutCardView(
    workout: WorkoutModel,
    onClickWorkout: (workout: WorkoutModel) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(64.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        shape = MaterialTheme.shapes.extraSmall,
        onClick = {
            onClickWorkout(workout)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = workout.type ?: "",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(text = formatDate(workout.date))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListViewPreview() {
    val userId = UUID.randomUUID()
    WorkoutsListView(
        listOf(
            WorkoutModel(_id = UUID.randomUUID(), date = Date(), type = "gym", userId = userId),
            WorkoutModel(_id = UUID.randomUUID(), date = Date(), type = "null", userId = userId),
            WorkoutModel(_id = UUID.randomUUID(), date = Date(), type = "running", userId = userId)
        ), {}
    )
}
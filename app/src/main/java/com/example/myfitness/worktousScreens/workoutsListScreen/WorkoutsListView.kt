package com.example.myfitness.worktousScreens.workoutsListScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfitness.worktousScreens.WorkoutModel
import com.example.myfitness.worktousScreens.workoutCard.WorkoutCardView
import java.util.Date
import java.util.UUID

@Composable
fun WorkoutsListView(
    workouts: List<WorkoutModel>,
    onClickWorkout: (workout: WorkoutModel) -> Unit
) {
    if (workouts.isEmpty())
        Text(text = "you have no workouts, weak..")
    else
        LazyColumn {
            items(workouts) {
                WorkoutCardView(it, onClickWorkout)
            }
        }
}

@Preview(showBackground = true)
@Composable
fun WorkoutsListPreview() {
    val userId = UUID.randomUUID()

    WorkoutsListView(
        listOf(
            WorkoutModel(_id = UUID.randomUUID(), date = Date(), type = "gym", userId = userId),
            WorkoutModel(_id = UUID.randomUUID(), date = Date(), type = "running", userId = userId),
        ), {}
    )
}
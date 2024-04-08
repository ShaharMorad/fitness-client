package com.example.myfitness.worktousScreens.calanderScreen

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfitness.worktousScreens.WorkoutModel
import java.util.Date
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import com.example.myfitness.utils.formatDate
import com.example.myfitness.worktousScreens.workoutCard.WorkoutCardView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarView(
    workouts: List<WorkoutModel>,
    onClickWorkout: (workout: WorkoutModel) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
        DatePicker(
            state = datePickerState,
            colors = getDatePickerColors(),
            modifier = Modifier.padding(16.dp),
            title = { Text(text = "see your workouts by calender") },
        )

        if (datePickerState.selectedDateMillis == null)
            Text("Select a date to see workouts")
        else {
            val selectedDate = Date(datePickerState.selectedDateMillis!!);
            val formattedDate = formatDate(selectedDate)
            val filteredWorkouts = workouts.filter { workout ->
                formatDate(workout.date) == formattedDate
            }
            if (filteredWorkouts.isEmpty())
                Text(text = "you have no workouts that day, weak..")
            else
                LazyColumn {
                    items(filteredWorkouts) {
                        WorkoutCardView(it, onClickWorkout)
                    }
                }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun getDatePickerColors(): DatePickerColors {
    return DatePickerDefaults.colors(
        containerColor = Color.Red,
        dayInSelectionRangeContainerColor = Color.Red,
        dayContentColor = Color.Blue,
        todayDateBorderColor = Color.Magenta,
        dayInSelectionRangeContentColor = Color.Yellow
    )
}

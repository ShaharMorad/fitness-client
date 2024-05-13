package com.example.myfitness.worktousScreens.editWorkoutScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfitness.loginRegisterScreen.CustomTextField
import java.util.Date

@Composable
fun EditWorkoutView(
    editWorkoutViewModel: EditWorkoutViewModel,
    afterSave: () -> Unit
) {
    val workoutType by editWorkoutViewModel.type
    val workoutTime by editWorkoutViewModel.time
    val initialWorkout by editWorkoutViewModel.workoutState

    fun onSave(type: String, date: Date) {
        editWorkoutViewModel.upsertUserWorkout(
            initialWorkout.copy(type = type, date = date)
        ) {
            afterSave()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomTextField(
            value = workoutType,
            onChange = { editWorkoutViewModel.onTypeChange(it) },
            placeholder = "workout type"
        )
        Text(text = workoutType, color = Color.Blue)

        TimePickerWithDialog(workoutTime) { hours, minutes ->
            editWorkoutViewModel.onTimeChange(hours, minutes)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onSave(workoutType, workoutTime) }) {
                Text(text = "save")
            }
        }
    }
}
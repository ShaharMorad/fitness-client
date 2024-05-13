package com.example.myfitness.worktousScreens.editWorkoutScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitness.serverService
import com.example.myfitness.worktousScreens.CreateUpdateWorkoutDto
import com.example.myfitness.worktousScreens.WorkoutModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.UUID

class EditWorkoutViewModel(workout: WorkoutModel?, private val userId: UUID) : ViewModel() {

    private val _workoutState = mutableStateOf(WorkoutState(workout))
    val workoutState: State<WorkoutState> = _workoutState

    private val _workoutResponseState = mutableStateOf(WorkoutResponseState())
    val workoutResponseState: State<WorkoutResponseState> = _workoutResponseState

    private val _time = mutableStateOf(workout?.date ?: Date())
    val time: State<Date> = _time

    private val _type = mutableStateOf(workout?.type ?: "")
    val type: State<String> = _type

    init {
        if (workout != null) fetchWorkout(workout.userId, workout._id)
    }

    fun onTypeChange(newString: String) {
        _type.value = newString
    }

    fun onTimeChange(hours:Int, minutes:Int){
        val calendar = Calendar.getInstance()
        calendar.time = time.value
        calendar.set(Calendar.MINUTE, minutes)
        calendar.set(Calendar.HOUR, hours)

        _time.value = calendar.time
    }


    fun setWorkout(workout: WorkoutModel?) {
        _workoutState.value = WorkoutState(workout)
    }

    private fun fetchWorkout(userId: UUID, workoutId: UUID) {
        _workoutResponseState.value = WorkoutResponseState(isLoading = true)

        viewModelScope.launch {
            try {
                val response = serverService.fetchUserWorkout(userId, workoutId)
                _workoutResponseState.value = _workoutResponseState.value.copy(
                    response = response,
                    isLoading = false,
                )
                _workoutState.value =
                    _workoutState.value.copy(type = response.type, date = response.date)
            } catch (e: Exception) {
                _workoutResponseState.value = _workoutResponseState.value.copy(
                    isLoading = false,
                    error = "Oops something went wrong while trying to fetch your workout... \n${e.message}"
                )
            }
        }
    }

    fun upsertUserWorkout(workout: WorkoutState, final: () -> Unit) {
        _workoutResponseState.value = WorkoutResponseState(isLoading = true)

        viewModelScope.launch {
            try {
                val response = if (workout.id != null)
                    serverService.updateUserWorkout(
                        userId, workout.id!!, CreateUpdateWorkoutDto(workout.type, workout.date)
                    )
                else
                    serverService.createUserWorkout(
                        userId, CreateUpdateWorkoutDto(workout.type, workout.date)
                    )
                _workoutResponseState.value = _workoutResponseState.value.copy(
                    response = response,
                    isLoading = false,
                )
                final()
            } catch (e: Exception) {
                _workoutResponseState.value = _workoutResponseState.value.copy(
                    isLoading = false,
                    error = "Oops something went wrong while trying to update your workout... \n${e.message}"
                )
            }
        }
    }

    data class WorkoutResponseState(
        val isLoading: Boolean = false,
        val response: WorkoutModel? = null,
        val error: String? = null
    )

    data class WorkoutState(
        var id: UUID? = null,
        var userId: UUID? = null,
        var date: Date = Date(),
        var type: String = "",
    ) {
        constructor(workout: WorkoutModel?) : this() {
            if (workout != null) {
                id = workout._id
                userId = workout.userId
                date = workout.date
                type = workout.type
            }
        }

    }
}
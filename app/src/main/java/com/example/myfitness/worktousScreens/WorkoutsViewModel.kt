package com.example.myfitness.worktousScreens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitness.loginRegisterScreen.UserModel
import com.example.myfitness.serverService
import kotlinx.coroutines.launch
import java.util.UUID

class WorkoutsViewModel(val user: UserModel) : ViewModel() {

    private val _allWorkoutsResponseState= mutableStateOf(WorkoutsResponseState())
    val allWorkoutsResponseState = _allWorkoutsResponseState

    private val _selectedWorkout = mutableStateOf<WorkoutModel?>(null)
    val selectedWorkout: State<WorkoutModel?> = _selectedWorkout

    init {
        fetchAllWorkouts(user._id)
    }
    fun setSelectedWorkout(workout: WorkoutModel?) {
        _selectedWorkout.value = workout
    }

    private fun fetchAllWorkouts(userId: UUID) {
        _allWorkoutsResponseState.value =
            WorkoutsResponseState(isLoading = true, response = listOf(), error = null)

        viewModelScope.launch {
            try {
                val response = serverService.fetchUserWorkouts(userId)
                _allWorkoutsResponseState.value = _allWorkoutsResponseState.value.copy(
                    response = response,
                    isLoading = false,
                )
            } catch (e: Exception) {
                _allWorkoutsResponseState.value = _allWorkoutsResponseState.value.copy(
                    isLoading = false,
                    error = "Oops something went wrong while trying to fetch your workouts... \n${e.message}"
                )
            }
        }
    }

    fun refetchAllWorkouts() {
        fetchAllWorkouts(user._id)
    }

    data class WorkoutsResponseState(
        val isLoading: Boolean = false,
        val response: List<WorkoutModel> = listOf(),
        val error: String? = null
    )
}
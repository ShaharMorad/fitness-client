package com.example.myfitness.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfitness.Page
import com.example.myfitness.worktousScreens.calanderScreen.CalendarView
import com.example.myfitness.worktousScreens.editWorkoutScreen.EditWorkoutView
import com.example.myfitness.loginRegisterScreen.UserModel
import com.example.myfitness.worktousScreens.WorkoutModel
import com.example.myfitness.worktousScreens.WorkoutsViewModel
import com.example.myfitness.worktousScreens.editWorkoutScreen.EditWorkoutViewModel
import com.example.myfitness.worktousScreens.workoutsListScreen.WorkoutsListView

@Composable
fun AppView(
    user: UserModel,
    handleDisconnect: () -> Unit
) {
    val pagerViewModel: PagerViewModel = viewModel()
    val workoutViewModel = WorkoutsViewModel(user)
    val workoutsState by workoutViewModel.allWorkoutsResponseState

    Scaffold(
        topBar = {
            AppBar(
                handleChangePage = { page ->
                    pagerViewModel.onChangePage(page)
                },
                handleDisconnect
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when {
                workoutsState.isLoading ->
                    CircularProgressIndicator()

                workoutsState.error != null ->
                    Text(text = "AppView ERROR: ${workoutsState.error}", color = Color.Red)

                else -> {
                    Pages(
                        pagerViewModel,
                        workoutViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Pages(
    pagerViewModel: PagerViewModel,
    workoutsViewModel: WorkoutsViewModel,
) {
    val workoutsState by workoutsViewModel.allWorkoutsResponseState
    val selectedWorkout by workoutsViewModel.selectedWorkout
    val editWorkoutViewModel = EditWorkoutViewModel(selectedWorkout, workoutsViewModel.user._id)

    fun onWorkoutClick(workout: WorkoutModel) {
        workoutsViewModel.setSelectedWorkout(workout)
        editWorkoutViewModel.setWorkout(workout)
        pagerViewModel.onChangePage(Page.EDIT_WORKOUT)
    }

    when (pagerViewModel.currentPage.value) {
        Page.WORKOUTS -> {
            WorkoutsListView(workoutsState.response) { onWorkoutClick(it) }
        }

        Page.COLANDER ->
            CalendarView(workoutsState.response) { onWorkoutClick(it) }

        Page.EDIT_WORKOUT ->
            EditWorkoutView(editWorkoutViewModel) {
                workoutsViewModel.refetchAllWorkouts()
                pagerViewModel.onChangePage(Page.WORKOUTS)
            }
    }
}


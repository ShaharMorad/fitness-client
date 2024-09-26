package com.example.myfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfitness.loginRegister.LoginRegister
import com.example.myfitness.loginRegister.LoginRegisterViewModel
import com.example.myfitness.ui.theme.MyFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: LoginRegisterViewModel = viewModel()
            val userState by viewModel.userResponseState
            MyFitnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    if (userState.response == null)
                    LoginRegister(viewModel)
//                    else //TODO: actual pages
//                        Text(text = "gotcha")
//                        MyScreen(currentPage)
//                    when (currentPage) {
//                        Pages.CALANDER ->
//                            Text(text = "CALANDER ${loggedUser?.firstName} ${loggedUser?.lastName}")
//
//
//                        Pages.WORKOUTS ->
//                            Text(text = "WORKOUTS ${loggedUser?.firstName} ${loggedUser?.lastName}")
//
//
//                        Pages.EDIT_WORKOUT ->
//                            Text(text = "EDIT_WORKOUT ${loggedUser?.firstName} ${loggedUser?.lastName}")
//
//                    }
                }
            }
        }
    }
}
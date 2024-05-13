package com.example.myfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfitness.app.AppView
import com.example.myfitness.loginRegisterScreen.LoginRegister
import com.example.myfitness.loginRegisterScreen.LoginRegisterViewModel
import com.example.myfitness.ui.theme.MyFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginRegisterViewModel: LoginRegisterViewModel = viewModel()
            val userState by loginRegisterViewModel.userResponseState
            val user = userState.response

            MyFitnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (user == null)
                        LoginRegister(loginRegisterViewModel)
                    else
                        AppView(user) {
                            loginRegisterViewModel.disconnect()
                        }
                }
            }
        }
    }
}
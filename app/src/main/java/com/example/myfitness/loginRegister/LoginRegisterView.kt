package com.example.myfitness.loginRegister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun LoginRegister(viewModel: LoginRegisterViewModel) {
    val userState by viewModel.userResponseState
    val isRegisterMode = viewModel.isRegisterMode.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            userState.isLoading -> {
                CircularProgressIndicator()
            }

            userState.error != null -> {
                Text(text = "ERROR: ${userState.error}", color = Color.Red)
            }

            userState.response != null -> {
                println(userState.response)
                Text(text = "GOOD: ${userState.response}", color = Color.Green)

                // what to do when i have response?
            }

            else -> {
                if (isRegisterMode) {
                    CustomTextField(
                        viewModel.fname.value,
                        { string ->
                            viewModel.onFnameChange(string)
                        },
                        "first name"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        viewModel.lname.value,
                        { string ->
                            viewModel.onLnameChange(string)
                        },
                        "first name"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                CustomTextField(
                    viewModel.email.value,
                    { string ->
                        viewModel.onEmailChange(string)
                    },
                    "email"
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField(
                    viewModel.password.value,
                    { string ->
                        viewModel.onPasswordChange(string)
                    },
                    "password"
                )
                Spacer(modifier = Modifier.height(8.dp))
                ClickableText(
                    text = buildAnnotatedString {
                        append("or ")
                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                color = Color.Blue
                            )
                        ) {
                            append(if (isRegisterMode) "login" else "register")
                        }
                    },
                    onClick = { viewModel.onToggleMode() }
                )
                Row {
                    Button(onClick = { if (isRegisterMode) viewModel.registerUser() else viewModel.loginUser() }) {
                        Text(if (isRegisterMode) "register" else "login")
                    }
                }
            }
        }
    }
}
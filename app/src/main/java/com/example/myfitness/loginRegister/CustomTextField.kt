package com.example.myfitness.loginRegister

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(value: String, onChange: (value: String) -> Unit, placeholder: String?) {
    OutlinedTextField(
        value = value,
        onValueChange = { changedValue ->
            onChange(changedValue)
        },
        placeholder = {
            Text(
                text = placeholder ?: "",
                style = TextStyle(color = Color.LightGray)

            )
        },
        textStyle = TextStyle(color = Color.DarkGray),
        maxLines = 1,
        modifier = Modifier
            .widthIn(min = 300.dp, max = 300.dp)
    )
}
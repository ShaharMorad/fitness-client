package com.example.myfitness.loginRegister

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class UserModel(
    val _id: UUID,
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
) : Parcelable

data class RegisterUserDto(
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
)

data class LoginUserDto(
    val password: String,
    val email: String,
)
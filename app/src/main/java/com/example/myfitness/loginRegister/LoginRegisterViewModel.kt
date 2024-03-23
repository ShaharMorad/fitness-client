package com.example.myfitness.loginRegister

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitness.serverService
import kotlinx.coroutines.launch

class LoginRegisterViewModel() : ViewModel() {
    private val _email = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _fname = mutableStateOf("")
    private val _lname = mutableStateOf("")
    private val _isRegisterMode = mutableStateOf(false)
    private val _userResponseState = mutableStateOf(UserResponseState())

    val email: State<String> = _email
    val password: State<String> = _password
    val fname: State<String> = _fname
    val lname: State<String> = _lname
    val isRegisterMode: State<Boolean> = _isRegisterMode
    val userResponseState: State<UserResponseState> = _userResponseState

    fun onEmailChange(newString: String) {
        _email.value = newString
    }

    fun onPasswordChange(newString: String) {
        _password.value = newString
    }

    fun onFnameChange(newString: String) {
        _fname.value = newString
    }

    fun onLnameChange(newString: String) {
        _lname.value = newString
    }

    fun onToggleMode() {
        _isRegisterMode.value = !_isRegisterMode.value
    }

    fun registerUser() {
        _userResponseState.value = initialUserResponse
        val registerUserData = RegisterUserDto(
            firstName = _fname.value,
            lastName = _lname.value,
            email = _email.value,
            password = _password.value,
        )

        viewModelScope.launch {
            try {
                val response = serverService.register(registerUserData)
                _userResponseState.value = UserResponseState(
                    response = response,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                println(e)
                _userResponseState.value = UserResponseState(
                    isLoading = false,
                    error = "Oops something went wrong while trying to register/login... \n${e.message}"
                )
            } catch (a: Throwable) {
                println(a)

            }
        }
    }

    fun loginUser() {
        _userResponseState.value = initialUserResponse

        val loginLambda: suspend () -> UserModel = suspend {
            serverService.login(
                LoginUserDto(
                    email = _email.value,
                    password = _password.value,
                )
            )
        }

        registerLoginUser(loginLambda)
    }

    private fun registerLoginUser(registerOrLogin: suspend () -> UserModel) {
        _userResponseState.value = initialUserResponse

        viewModelScope.launch {
            try {
                val response = registerOrLogin()
                _userResponseState.value = _userResponseState.value.copy(
                    response = response,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                println(e)
                _userResponseState.value = _userResponseState.value.copy(
                    isLoading = false,
                    error = "Oops something went wrong while trying to register/login... \n${e.message}"
                )
            }
        }
    }

    private val initialUserResponse =
        UserResponseState(
            response = null,
            isLoading = true,
            error = null
        )

    data class UserResponseState(
        val isLoading: Boolean = false,
        val response: UserModel? = null,
        val error: String? = null
    )
}
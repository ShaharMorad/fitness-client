package com.example.myfitness.loginRegisterScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitness.serverService
import kotlinx.coroutines.launch

class LoginRegisterViewModel() : ViewModel() {
    private val _email = mutableStateOf("asd@asd.asd")
    private val _password = mutableStateOf("asd")
    private val _fisrtName = mutableStateOf("")
    private val _lastName = mutableStateOf("")
    private val _isRegisterMode = mutableStateOf(false)
    private val _userResponseState = mutableStateOf(UserResponseState())

    val email: MutableState<String> = _email
    val password: MutableState<String> = _password
    val firstName: MutableState<String> = _fisrtName
    val lastName: MutableState<String> = _lastName
    val isRegisterMode: MutableState<Boolean> = _isRegisterMode
    val userResponseState: State<UserResponseState> = _userResponseState

    fun onEmailChange(newString: String) {
        _email.value = newString
    }

    fun onPasswordChange(newString: String) {
        _password.value = newString
    }

    fun onFirstNameChange(newString: String) {
        _fisrtName.value = newString
    }

    fun onLastNameChange(newString: String) {
        _lastName.value = newString
    }

    fun onToggleMode() {
        _isRegisterMode.value = !_isRegisterMode.value
    }

    fun registerUser(firstName: String, lastName: String, email: String, password: String) {
        val registerLambda: suspend () -> UserModel = suspend {
            serverService.register(
                RegisterUserDto(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                )
            )
        }

        registerOrLogin(registerLambda)
    }

    fun loginUser(email: String, password: String) {
        val loginLambda: suspend () -> UserModel = suspend {
            serverService.login(
                LoginUserDto(
                    email = email,
                    password = password,
                )
            )
        }

        registerOrLogin(loginLambda)
    }

    private fun registerOrLogin(lambda: suspend () -> UserModel) {
        _userResponseState.value = UserResponseState(isLoading = true)

        viewModelScope.launch {
            try {
                val response = lambda()
                _userResponseState.value = _userResponseState.value.copy(
                    response = response,
                    isLoading = false,
                )
            } catch (e: Exception) {
                _userResponseState.value = _userResponseState.value.copy(
                    isLoading = false,
                    error = "Oops something went wrong while trying to login/register... \n${e.message}"
                )
            } finally {
                initRegistration()
            }
        }
    }

    private fun initRegistration() {
        _email.value = ""
        _password.value = ""
        _fisrtName.value = ""
        _lastName.value = ""
        _isRegisterMode.value = false
    }

    fun disconnect() {
        _userResponseState.value = UserResponseState()
    }

    data class UserResponseState(
        val isLoading: Boolean = false,
        val response: UserModel? = null,
        val error: String? = null
    )
}
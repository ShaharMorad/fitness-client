package com.example.myfitness.loginRegister

import androidx.compose.runtime.MutableState
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

    val email: MutableState<String> = _email
    val password: MutableState<String> = _password
    val fname: MutableState<String> = _fname
    val lname: MutableState<String> = _lname
    val isRegisterMode: MutableState<Boolean> = _isRegisterMode
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
        viewModelScope.launch {
            try {
                val response = serverService.register(
                    RegisterUserDto(
                        firstName = _fname.value,
                        lastName = _lname.value,
                        email = _email.value,
                        password = _password.value,
                    )
                )
                _userResponseState.value = _userResponseState.value.copy(
                    response = response,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                println(e)
                _userResponseState.value = _userResponseState.value.copy(
                    isLoading = false,
                    error = "Oops something went wrong while trying to register... \n${e.message}"
                )
            }
        }
    }

    fun loginUser() {
        _userResponseState.value = _userResponseState.value.copy(
            response = null,
            isLoading = false,
            error = null
        )

        viewModelScope.launch {
            try {
                val response = serverService.login(
                    LoginUserDto(
                        email = _email.value,
                        password = _password.value,
                    )
                )
                _userResponseState.value = _userResponseState.value.copy(
                    response = response,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _userResponseState.value = _userResponseState.value.copy(
                    isLoading = false,
                    error = "Oops something went wrong while trying to login... \n${e.message}"
                )
            }
        }
    }

    data class UserResponseState(
        val isLoading: Boolean = false,
        val response: UserModel? = null,
        val error: String? = null
    )
}
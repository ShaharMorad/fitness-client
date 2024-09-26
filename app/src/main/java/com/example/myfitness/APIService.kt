package com.example.myfitness

import com.example.myfitness.loginRegister.LoginUserDto
import com.example.myfitness.loginRegister.RegisterUserDto
import com.example.myfitness.loginRegister.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:3000/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface APIService {
    @POST("users")
    suspend fun register(@Body user: RegisterUserDto): UserModel

    @POST("users/login") //TODO: implement in server
    suspend fun login(@Body user: LoginUserDto): UserModel

}

val serverService = retrofit.create(APIService::class.java)
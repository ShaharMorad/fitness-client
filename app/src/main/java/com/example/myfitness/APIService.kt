package com.example.myfitness

import com.example.myfitness.loginRegisterScreen.LoginUserDto
import com.example.myfitness.loginRegisterScreen.RegisterUserDto
import com.example.myfitness.loginRegisterScreen.UserModel
import com.example.myfitness.worktousScreens.CreateUpdateWorkoutDto
import com.example.myfitness.worktousScreens.WorkoutModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:3000/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface APIService {
    @POST("users")
    suspend fun register(@Body user: RegisterUserDto): UserModel

    @POST("users/login")
    suspend fun login(@Body user: LoginUserDto): UserModel

    @GET("users/{userid}/workouts")
    suspend fun fetchUserWorkouts(@Path("userid") id: UUID): List<WorkoutModel>

    @GET("users/{userid}/workouts/{workoutid}")
    suspend fun fetchUserWorkout(
        @Path("userid") userId: UUID,
        @Path("workoutid") workoutId: UUID
    ): WorkoutModel

    @PATCH("users/{userid}/workouts/{workoutid}")
    suspend fun updateUserWorkout(
        @Path("userid") userId: UUID,
        @Path("workoutid") workoutId: UUID,
        @Body workout: CreateUpdateWorkoutDto
    ): WorkoutModel

    @PATCH("users/{userid}/workouts/{workoutid}")
    suspend fun createUserWorkout(
        @Path("userid") userId: UUID,
        @Body workout: CreateUpdateWorkoutDto
    ): WorkoutModel
}

val serverService = retrofit.create(APIService::class.java)
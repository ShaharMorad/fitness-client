package com.example.myfitness.worktousScreens

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

@Parcelize
data class WorkoutModel(
  val _id: UUID,
  val userId: UUID,
  val date: Date,
  val type: String
) : Parcelable

data class CreateUpdateWorkoutDto(
  val date: String,
  val type: String,
) {
  @SuppressLint("SimpleDateFormat")
  constructor(type: String, date: Date) :
      this(
        SimpleDateFormat(
          "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        ).format(date),
        type
      )
}

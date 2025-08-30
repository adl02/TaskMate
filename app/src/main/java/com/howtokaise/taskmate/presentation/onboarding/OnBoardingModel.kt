package com.howtokaise.taskmate.presentation.onboarding

import androidx.annotation.DrawableRes
import com.howtokaise.taskmate.R

sealed class OnBoardingModel(
    val title : String,
    val description : String,
    @DrawableRes
    val image : Int
) {
    data object FirstPage : OnBoardingModel(
        title = "Organize Your Day",
        description = "Stay on top of your tasks with a simple and powerful To-Do List app.",
        image = R.drawable.first
    )
    data object SecondPage : OnBoardingModel(
        title = "Add, Edit, Delete",
        description = "Quickly add new tasks, update them, or remove the ones you don’t need.",
        image = R.drawable.second
    )
    data object ThirdPage : OnBoardingModel(
        title = "Track Your Progress",
        description = "Mark tasks as completed and achieve your daily goals.",
        image = R.drawable.third
    )
}
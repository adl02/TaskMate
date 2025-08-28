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
        title = "Hey there its me",
        description = "Hey there its me your love my love",
        image = R.drawable.to_to
    )
    data object SecondPage : OnBoardingModel(
        title = "Hey there its me",
        description = "Hey there its me your love my love",
        image = R.drawable.to_to
    )
    data object ThirdPage : OnBoardingModel(
        title = "Hey there its me",
        description = "Hey there its me your love my love",
        image = R.drawable.to_to
    )
}
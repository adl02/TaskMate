package com.howtokaise.taskmate.domain.navigation

sealed class Route (
    val route: String
){
    object HomeScreen : Route("HomeScreen")
    object EditScreen : Route("EditScreen")
    object OnBoarding : Route("OnBoarding")
}
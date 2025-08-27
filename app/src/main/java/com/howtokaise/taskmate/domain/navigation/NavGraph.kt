package com.howtokaise.taskmate.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.howtokaise.taskmate.Presentation.onboarding.OnBoardingScreen
import com.howtokaise.taskmate.Presentation.screens.EditScreen
import com.howtokaise.taskmate.Presentation.screens.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Route.HomeScreen.route,
    ){
        composable(Route.HomeScreen.route){
            HomeScreen()
        }
        composable(Route.EditScreen.route){
            EditScreen()
        }
        composable(Route.OnBoarding.route){
            OnBoardingScreen()
        }
    }
}
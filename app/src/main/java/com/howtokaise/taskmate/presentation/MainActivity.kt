package com.howtokaise.taskmate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.room.Room
import com.howtokaise.taskmate.domain.database.TaskDatabase
import com.howtokaise.taskmate.domain.database.TaskRepository
import com.howtokaise.taskmate.presentation.onboarding.OnBoardingScreen
import com.howtokaise.taskmate.presentation.onboarding.OnBoardingUtils
import com.howtokaise.taskmate.presentation.screens.HomeScreen
import com.howtokaise.taskmate.presentation.ui.theme.TaskMateTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnBoardingUtils(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "TaskMate"
        ).fallbackToDestructiveMigration().build()
        val repository = TaskRepository(db.taskDao())
        val viewmodel = AppViewmodel(repository)
        setContent {
            TaskMateTheme {
                val isDark = isSystemInDarkTheme()
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (isDark) Color.Black else Color.White)
                        .navigationBarsPadding()
                ) {
                    if (onboardingUtils.isOnboardingCompleted()) {
                        HomeScreen(viewmodel)
                    } else {
                        Boarding(viewmodel)
                    }
                }
            }
        }
    }

    @Composable
    private fun Boarding(viewmodel: AppViewmodel) {
        val scope = rememberCoroutineScope()
        OnBoardingScreen {
            onboardingUtils.setOnboardingCompleted()
            scope.launch {
                setContent {
                    TaskMateTheme {
                        HomeScreen(viewmodel)
                    }
                }
            }
        }
    }
}

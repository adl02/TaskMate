package com.howtokaise.taskmate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.howtokaise.taskmate.presentation.onboarding.OnBoardingScreen
import com.howtokaise.taskmate.presentation.onboarding.OnBoardingUtils
import com.howtokaise.taskmate.presentation.ui.theme.TaskMateTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnBoardingUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskMateTheme {
                Surface(
                    modifier = Modifier.navigationBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (onboardingUtils.isOnboardingCompleted()) {
                        Show()
                    } else {
                        ll()
                    }
                }
            }
        }
    }

    @Composable
    private fun Show() {
        Column {
            Text(text = "HOme Screen")
        }
    }

    @Composable
    private fun ll() {
        val scope = rememberCoroutineScope()
        OnBoardingScreen {
            onboardingUtils.setOnboardingCompleted()
            scope.launch {
                setContent {
                    Show()
                }
            }
        }
    }
}

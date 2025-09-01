package com.howtokaise.taskmate.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardingGraphUI(OnBoardingModel : OnBoardingModel) {

    Column {
        Image(
            painter = painterResource(id = OnBoardingModel.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        )
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = OnBoardingModel.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = OnBoardingModel.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp,0.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(50.dp))
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun hey(modifier: Modifier = Modifier) {
    OnBoardingGraphUI(OnBoardingModel.FirstPage)
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ey(modifier: Modifier = Modifier) {
    OnBoardingGraphUI(OnBoardingModel.SecondPage)
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun y(modifier: Modifier = Modifier) {
    OnBoardingGraphUI(OnBoardingModel.ThirdPage)
}
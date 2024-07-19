package com.humanocomputador.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HCProgressCharge(
    isLoading: Boolean,
    progress: Float,
    text: String = "",
    modifier: Modifier = Modifier
) {
    val progressAnim by animateFloatAsState(
        targetValue = if (isLoading) progress else 0f,
        animationSpec = tween(durationMillis = 500)
    )
    val offsetAnim by animateDpAsState(
        targetValue = if (isLoading) 0.dp else 300.dp,
        animationSpec = tween(durationMillis = 500)
    )

    if (isLoading) {
        Box(
            modifier = modifier
                //.padding(0.dp)
                .padding(top = 50.dp)
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .width(200.dp)
                .padding(horizontal = 16.dp)
                .offset(x = offsetAnim)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(8.dp)
            ) {
                HCPercentageProgress(progress = progressAnim)
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewProgressCharge() {
//    // Use a state to simulate the loading process
//    val isLoading = remember { mutableStateOf(true) }
//
//    LaunchedEffect(Unit) {
//        // Simulate a delay before hiding the progress indicator
//        delay(3000) // Adjust the delay to see the animation
//        isLoading.value = false
//    }
//
//    HCProgressCharge(
//        isLoading = isLoading.value,
//        progress = 0.5f,
//        text = "Cargando recursos"
//    )
//}


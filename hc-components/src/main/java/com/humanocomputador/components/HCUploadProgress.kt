package com.humanocomputador.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay

@Composable
fun HCUploadProgress(
    //progress: Float
) {
    var progress by remember { mutableStateOf(0.0f) }

    LaunchedEffect(true) {

        for (i in 0..100 step 10) {
            delay(300)

            if (i == 100) {
                cancel()
            }

            progress = i / 100f
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column(
        Modifier
            .padding(all = 16.dp)
            .fillMaxSize()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(4.dp))
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        HCPercentageProgress(progress = animatedProgress)

        Spacer(Modifier.height(16.dp))

        Text(text = "Subiendo archivo...")

        Spacer(Modifier.height(8.dp))

        OutlinedButton(onClick = { }) {
            Text(text = "Cancelar")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCUploadProgress()
//}


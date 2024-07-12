package com.humanocomputador.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HCPercentageProgress(
    progress: Float
) {
    val percentage: Int = (progress * 100).toInt()

    Box(contentAlignment = Alignment.Center) {
        Text(text = "$percentage%", style = MaterialTheme.typography.bodyMedium)
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(70.dp),
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCPercentageProgress(progress = 0.5f)
//}
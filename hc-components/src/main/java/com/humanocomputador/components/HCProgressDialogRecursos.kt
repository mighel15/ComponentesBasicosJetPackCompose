package com.humanocomputador.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HCProgressDialogRecursos(
    isLoading: Boolean,
    progress: Float,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = ""
) {
    val progresoAnimado by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )
    if (isLoading) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {},
            dismissButton = {},
            title = {},
            text = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.padding(16.dp).fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HCPercentageProgress(progress = progresoAnimado)
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 1.dp
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewProgressDialogRecursos(){
//    HCProgressDialogRecursos(
//        isLoading = true,
//        progress = 0.5f,
//        text = "Cargando recursos",
//        onDismiss = {}
//    )
//}


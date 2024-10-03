package com.humanocomputador.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.humanocomputador.components.R

@Composable
fun HCProgressCustomDialog(
    isLoading: Boolean,
    onDismiss: () -> Unit,
    text: String = "Cargando...",
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_cargando_cubos)
    )
    val reproduciendo by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.8f
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
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnimation(
                            composition,
                            progress = { reproduciendo },
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .weight(1f),
                        )

                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 20.dp)
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
//private fun PreviewVisualizacion(){
//    HCProgressCustomDialog(
//        isLoading = true,
//        onDismiss = {}
//    )
//}
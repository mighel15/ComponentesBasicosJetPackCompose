package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun HCCardPermisos(
    modifier: Modifier = Modifier.padding(top = 10.dp),
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    lottieResId: Int,
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(lottieResId)
    )
    val reproduciendo by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.5f
    )

    val gradientColors = listOf(
        Color(android.graphics.Color.parseColor("#4D5E88")),
        //colorResource(id = R.color.disabled_button),
        Color(android.graphics.Color.parseColor("#FFFFFF")),
        //colorResource(id = R.color.cr_blanco)
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = contentColorFor(Color.Transparent)
        ),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = border,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = gradientColors,
                        startX = 0f,
                        endX = 500f
                    ),
                )
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                LottieAnimation(
                    composition,
                    progress = { reproduciendo },
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f),
                )

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(2f)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    HCButtonSolid(
                        text = buttonText,
                        icon = painterResource(R.drawable.ico_check_permissions_24),
                        onClick = onClick,
                        modifier = Modifier.padding(top = 2.dp),
                        enabled = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewVisualizacion(){
    HCCardPermisos(
        lottieResId = R.raw.anim_alert,
        text = "Tu texto aquí",
        onClick = {},
        buttonText = "Conceder Permisos",
    )
}
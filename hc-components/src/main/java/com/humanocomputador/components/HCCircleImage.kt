package com.humanocomputador.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun HCCircleImage(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(100.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCCircleImage(
//        painter = painterResource(id = R.drawable.img_example_profile),
//        contentDescription = "Avatar de prueba"
//    )
//}
package com.humanocomputador.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HCCircleAvatar(
    painter: Painter,
    contentDescription: String,
    size: Dp = 100.dp,
    modifier: Modifier = Modifier.border(2.dp, Color.Gray, CircleShape)
) {
    Box(
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                //.offset(y = (-size * 0.1f)) //Realiza un elevacion para que centre el rostro
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCCircleAvatar(
//        painter = painterResource(id = R.drawable.img_example_profile),
//        contentDescription = "Avatar de prueba"
//    )
//}
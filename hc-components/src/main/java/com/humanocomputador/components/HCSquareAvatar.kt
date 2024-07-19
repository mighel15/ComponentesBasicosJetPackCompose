package com.humanocomputador.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun HCSquareAvatar(
    painter: Painter,
    contentDescription: String?,
    size: Dp = 100.dp,
    borderColor: Color = Color.Gray,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 20.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(cornerRadius))
            .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion() {
//    HCSquareAvatar(
//        painter = painterResource(id = R.drawable.img_example_profile),
//        contentDescription = "Profile Image",
//        size = 100.dp,
//        borderColor = Color.Gray,
//        borderWidth = 2.dp,
//        cornerRadius = 20.dp,
//    )
//}
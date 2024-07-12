package com.humanocomputador.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun HCImageVisualizer (
    painter: Painter,
    contentDescription: String?,
    onClose: () -> Unit
) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }

    AlertDialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        confirmButton = {
            HCButton(
                onClick = onClose,
                text = "Cerrar",
                icon = painterResource(R.drawable.ico_cerrar_24)
            )
        },
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        text = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, rotation ->
                            scale.value *= zoom
                            rotationState.value += rotation
                            offsetX.value += pan.x
                            offsetY.value += pan.y
                        }
                    }
            ) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale.value,
                            scaleY = scale.value,
                            //Habilitar para rotar la imagen
                            //rotationZ = rotationState.value,
                            translationX = offsetX.value,
                            translationY = offsetY.value
                        )
                )
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCImageVisualizer(
//        painter = painterResource(id = R.drawable.img_example_profile),
//        contentDescription = "Descripción de la imagen",
//        onClose = { }
//    )
//}
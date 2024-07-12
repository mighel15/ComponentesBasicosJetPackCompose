package com.humanocomputador.components

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun HCDrawingCanvas(
    modifier: Modifier = Modifier,
    onDone: (Bitmap) -> Unit
) {
    data class PathWithPaint(val path: android.graphics.Path, val paint: android.graphics.Paint)
    val bitmap = remember { Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888) }
    val canvas = Canvas(bitmap)
    val paths = remember { mutableStateListOf<PathWithPaint>() }
    val currentPath = remember { mutableStateOf(android.graphics.Path()) }
    val paint = remember {
        android.graphics.Paint().apply {
            color = Color.Black.toArgb()
            strokeWidth = 5f
            style = android.graphics.Paint.Style.STROKE
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        currentPath.value = android.graphics.Path().apply {
                            moveTo(offset.x, offset.y)
                        }
                    },
                    onDragEnd = {
                        paths.add(PathWithPaint(currentPath.value, paint))
                        canvas.drawPath(currentPath.value, paint)
                        currentPath.value = android.graphics.Path()
                    },
                    onDrag = { change, _ ->
                        currentPath.value.lineTo(change.position.x, change.position.y)
                        paths.add(PathWithPaint(android.graphics.Path(currentPath.value), paint))
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas { drawCanvas ->
                paths.forEach { pathWithPaint ->
                    drawCanvas.nativeCanvas.drawPath(pathWithPaint.path, pathWithPaint.paint)
                }
            }
        }
        IconButton(
            onClick = {
                coroutineScope.launch {
                    onDone(bitmap)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Save Drawing"
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewCanvaDrawer(){
//    HCDrawingCanvas(
//        onDone = {  }
//    )
//}
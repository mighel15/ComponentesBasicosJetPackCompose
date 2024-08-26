package com.humanocomputador.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.sqrt

@Composable
fun HCSpacePDFVisor(
    context: Context,
    uri: Uri,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    onPageChange: (Int) -> Unit
) {
    val rendererScope = rememberCoroutineScope()
    val mutex = remember { Mutex() }
    val renderer by produceState<PdfRenderer?>(null, uri) {
        rendererScope.launch(Dispatchers.IO) {
            val fileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")
            if (fileDescriptor != null) {
                value = PdfRenderer(fileDescriptor)
            }
        }
        awaitDispose {
            val currentRenderer = value
            rendererScope.launch(Dispatchers.IO) {
                mutex.withLock {
                    currentRenderer?.close()
                }
            }
        }
    }

    val zoomFactor = remember { mutableStateOf(1f) }
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index -> onPageChange(index + 1) }
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val pageCount by remember(renderer) { derivedStateOf { renderer?.pageCount ?: 0 } }
        val width = with(LocalDensity.current) { maxWidth.toPx() }.toInt()
        val height = (width * sqrt(2f)).toInt()

        LazyColumn(
            state = listState,
            verticalArrangement = verticalArrangement,
            modifier = Modifier
                .fillMaxSize()
                .scale(zoomFactor.value)
        ) {
            items(count = pageCount, key = { index -> "$uri-$index" }) { index ->
                var bitmap: Bitmap? by remember { mutableStateOf(null) }

                LaunchedEffect(uri, index) {
                    val destinationBitmap = Bitmap.createBitmap(
                        (width * zoomFactor.value).toInt(),
                        (height * zoomFactor.value).toInt(),
                        Bitmap.Config.ARGB_8888
                    )
                    mutex.withLock {
                        try {
                            renderer?.openPage(index)?.use { page ->
                                page.render(
                                    destinationBitmap,
                                    null,
                                    null,
                                    PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                                )
                            }
                        } catch (e: Exception) {
                        }
                    }
                    bitmap = destinationBitmap
                }

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "Page ${index + 1} of $pageCount",
                        modifier = Modifier
                            .background(Color.White)
                            .aspectRatio(1f / sqrt(2f))
                            .fillMaxWidth()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .aspectRatio(1f / sqrt(2f))
                            .fillMaxWidth()
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(6.dp)
        ) {
            HCIconButtonSolid(
                icon = painterResource(id = R.drawable.ico_minus_24),
                onClick = {zoomFactor.value = (zoomFactor.value - 0.1f).coerceAtLeast(1f)},
                description = "Zoom out",
                colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#003B73")))
            )

            Spacer(modifier = Modifier.width(1.dp))

            HCIconButtonSolid(
                icon = painterResource(id = R.drawable.ico_add_24),
                onClick = {zoomFactor.value = (zoomFactor.value + 0.1f).coerceAtMost(3f)},
                description = "Zoom in",
                colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#003B73")))
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewHCSpacePDFVisor() {
//    HCSpacePDFVisor(
//        context = LocalContext.current,
//        uri = Uri.parse("content://path/to/sample.pdf"),
//        onPageChange = {}
//    )
}
package com.humanocomputador.components

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.sqrt

@Composable
fun HCDocumentPDFVisor(
    modifier: Modifier = Modifier,
    uri: Uri,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    onPageChange: (Int) -> Unit
) {
    val rendererScope = rememberCoroutineScope()
    val mutex = remember { Mutex() }
    val renderer by produceState<PdfRenderer?>(null, uri) {
        rendererScope.launch(Dispatchers.IO) {
            val input = ParcelFileDescriptor.open(uri.toFile(), ParcelFileDescriptor.MODE_READ_ONLY)
            value = PdfRenderer(input)
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
    val context = LocalContext.current
    val imageLoader = LocalContext.current.imageLoader
    val imageLoadingScope = rememberCoroutineScope()
    val zoomFactor = remember { mutableStateOf(1f) }

    // Add zoom controls
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val width = with(LocalDensity.current) { maxWidth.toPx() }.toInt()
        val height = (width * sqrt(2f)).toInt()
        val pageCount by remember(renderer) { derivedStateOf { renderer?.pageCount ?: 0 } }
        val listState = rememberLazyListState()

        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemIndex }
                .distinctUntilChanged()
                .collect { index -> onPageChange(index + 1) }
        }

        LazyColumn(
            state = listState,
            verticalArrangement = verticalArrangement,
            modifier = Modifier
                .fillMaxSize()
                .scale(zoomFactor.value)
        ) {
            items(
                count = pageCount,
                key = { index -> "$uri-$index" }
            ) { index ->
                val cacheKey = MemoryCache.Key("$uri-$index")
                val cacheValue: Bitmap? = imageLoader.memoryCache?.get(cacheKey)?.bitmap

                var bitmap: Bitmap? by remember { mutableStateOf(cacheValue) }
                if (bitmap == null) {
                    DisposableEffect(uri, index) {
                        val job = imageLoadingScope.launch(Dispatchers.IO) {
                            val destinationBitmap = Bitmap.createBitmap(
                                (width * zoomFactor.value).toInt(),
                                (height * zoomFactor.value).toInt(),
                                Bitmap.Config.ARGB_8888
                            )
                            mutex.withLock {
                                Log.d("PdfGenerator", "Loading PDF $uri - page $index/$pageCount")
                                if (!coroutineContext.isActive) return@launch
                                try {
                                    renderer?.let {
                                        it.openPage(index).use { page ->
                                            page.render(
                                                destinationBitmap,
                                                null,
                                                null,
                                                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                                            )
                                        }
                                    }
                                } catch (e: Exception) {
                                    return@launch
                                }
                            }
                            bitmap = destinationBitmap
                        }
                        onDispose {
                            job.cancel()
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .aspectRatio(1f / sqrt(2f))
                            .fillMaxWidth()
                    )
                } else {
                    val request = ImageRequest.Builder(context)
                        .size((width * zoomFactor.value).toInt(), (height * zoomFactor.value).toInt())
                        .memoryCacheKey(cacheKey)
                        .data(bitmap)
                        .build()

                    Image(
                        modifier = Modifier
                            .background(Color.White)
                            .aspectRatio(1f / sqrt(2f))
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit,
                        painter = rememberAsyncImagePainter(request),
                        contentDescription = "Page ${index + 1} of $pageCount"
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
                description = "Zoom out"
            )

            Spacer(modifier = Modifier.width(2.dp))

            HCIconButtonSolid(
                icon = painterResource(id = R.drawable.ico_add_24),
                onClick = {zoomFactor.value = (zoomFactor.value + 0.1f).coerceAtMost(3f)},
                description = "Zoom in"
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewDocumentPDFVisor(){
//    HCDocumentPDFVisor(
//        uri = Uri.parse("content://com.android.providers.downloads.documents/document/1"),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        onPageChange = {}
//    )
//}
package com.humanocomputador.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun HCGridImagenes(
    imageUrls: List<String>,
    columns: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(8.dp)) {
        imageUrls.chunked(columns).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { imageUrl ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                if (row.size < columns) {
                    repeat(columns - row.size) {
                        Spacer(modifier = Modifier.weight(1f).aspectRatio(1f).padding(4.dp))
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    val imageUrls = listOf(
//        "https://example.com/image1.jpg",
//        "https://example.com/image2.jpg",
//    )
//
//    HCGridImagenes(
//        imageUrls = imageUrls,
//        columns = 3
//    )
//}
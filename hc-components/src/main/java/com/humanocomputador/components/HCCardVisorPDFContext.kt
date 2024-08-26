package com.humanocomputador.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha

@Composable
fun HCCardVisorPDFContext(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = Color.Black,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    title: String,
    pageInfo: String,
    uri: Uri,
    onPageChange: (Int) -> Unit,
    iconButtons: @Composable RowScope.() -> Unit,
    context: Context
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = background, contentColor = contentColor),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = border,
        modifier = modifier
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = pageInfo,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Row {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Box(
                            Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.align(Alignment.CenterEnd), content = iconButtons)
                        }
                    }
                }
            }

            HCSpacePDFVisor(
                context = context,
                uri = uri,
                modifier = Modifier,
                onPageChange = onPageChange
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    val uri = Uri.EMPTY
//    var currentPage by remember { mutableStateOf(1) }
//    val totalPages = 10
//    val context = LocalContext.current
//    HCCardVisorPDFContext(
//        title = "Título del Documento",
//        pageInfo = "Página $currentPage de $totalPages",
//        uri = uri,
//        context = context,
//        onPageChange = { page ->
//            currentPage = page
//        },
//        iconButtons = {
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Default.Place,
//                    contentDescription = "Aumentar Zoom",
//                    tint = Color.White
//                )
//            }
//            IconButton(onClick = {  }) {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = "Disminuir Zoom",
//                    tint = Color.White
//                )
//            }
//        },
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//    )
//}
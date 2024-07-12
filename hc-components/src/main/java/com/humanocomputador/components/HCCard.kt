package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha

@Composable
fun HCCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    thumbnailPainter: Painter,
    title: String,
    subtitle: String,
    mainImagePainter: Painter,
    mainImageContentDescription: String? = null,
    helpText: String,
    actionButtons: @Composable RowScope.() -> Unit,
    iconButtons: @Composable RowScope.() -> Unit
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                HCCircleAvatar(
                    painter = thumbnailPainter,
                    contentDescription = "Avatar de persona",
                    size = 40.dp
                )

                Spacer(modifier = Modifier.width(32.dp))

                Column(Modifier.fillMaxWidth()) {
                    Text(text = title, style = MaterialTheme.typography.titleLarge)
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = subtitle, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            Image(
                painter = mainImagePainter,
                contentDescription = mainImageContentDescription,
                Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(194.dp)
            )

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = helpText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Box(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                ) {
                    // Botones de acción
                    Row(modifier = Modifier.align(Alignment.CenterStart), content = actionButtons)

                    // Iconos
                    Row(modifier = Modifier.align(Alignment.CenterEnd), content = iconButtons)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCCard(
//        thumbnailPainter = painterResource(id = R.drawable.img_example_profile),
//        title = "Título de la tarjeta",
//        subtitle = "Subtítulo de la tarjeta",
//        mainImagePainter = painterResource(id = R.drawable.img_foto_perfil_example),
//        helpText = "Texto de ayuda que puede ser largo y descriptivo.",
//        actionButtons = {
//            TextButton(onClick = {}) {
//                Text(text = "ACCIÓN 1")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            TextButton(onClick = {}) {
//                Text(text = "ACCIÓN 2")
//            }
//        },
//        iconButtons = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Lock, contentDescription = null)
//            }
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Share, contentDescription = null)
//            }
//        }
//    )
//}
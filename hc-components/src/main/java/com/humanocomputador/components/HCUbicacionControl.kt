package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha
import com.humanocomputador.components.HCCircleAvatar
import com.humanocomputador.components.R

@Composable
fun HCUbicacionControl(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    cordenadas: String,
    mainImageContentDescription: String? = null,
    onClick: () -> Unit,
    miniMapThumbnail: @Composable (() -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = background, contentColor = contentColor),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = border,
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .size(40.dp)
                        .aspectRatio(0.5f)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    HCCircleAvatar(
                        painter = painterResource(id = R.drawable.img_direccion_tag),
                        contentDescription = "Icono de ubicacion",
                        size = 40.dp,
                        modifier = Modifier.border(0.dp, Color.Gray, CircleShape),
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    Modifier
                        .weight(4f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ubicacion de Domicilio",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = cordenadas,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }


                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = onClick,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Menu"
                        )
                    }
                }
            }

            miniMapThumbnail?.invoke() ?: Image(
                painter = painterResource(R.drawable.img_foto_perfil_anonimo_example), // Puedes cambiar por tu imagen por defecto
                contentDescription = mainImageContentDescription ?: "Miniatura de mapa",
                Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(194.dp)
                    .clickable { onClick() }
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCUbicacionControl(
//        cordenadas = "Cordenadas",
//        mainImageContentDescription = "Ubicacion de domicilio",
//        onClick = {}
//    )
//}

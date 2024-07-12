package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha

@Composable
fun HCItemAudioPlayer(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    thumbnailPainter: Painter,
    title: String,
    subtitle: String,
    menuItems: List<String>,
    onMenuItemClick: (String) -> Unit,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onStopClick: () -> Unit,
    enabledPlay: Boolean = true,
    enabledPause: Boolean = true,
    enabledStop: Boolean = true,
    progress: Float,
    currentTime: String,
    totalTime: String,
    esPrincipal: Boolean = false
) {
    var menuExpandido by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = background, contentColor = contentColor),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = border,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            HCCircleIcon(
                painter = thumbnailPainter,
                contentDescription = "Icono de grabacion",
                size = 60.dp
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                Modifier
                    .weight(4f)
                    //.fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                Modifier
                    .weight(1f),
                    //.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = { menuExpandido = true }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }

                DropdownMenu(
                    expanded = menuExpandido,
                    onDismissRequest = { menuExpandido = false }
                ) {
                    menuItems.forEach { menuItem ->
                        DropdownMenuItem(
                            text = { Text(menuItem) },
                            onClick = {
                                menuExpandido = false
                                onMenuItemClick(menuItem)
                            }
                        )
                    }
                }
                if (esPrincipal){
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "Principal",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                                //color = colorResource(id = R.color.cr_azul)
                            ),
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currentTime,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = totalTime,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onStopClick,
                enabled = enabledStop
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ico_stop_audio_24),
                    contentDescription = "Stop",
                    modifier = Modifier.size(36.dp),
                    tint = if (enabledStop) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = onPlayClick,
                enabled = enabledPlay
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ico_play_audio_24),
                    contentDescription = "Play",
                    modifier = Modifier.size(70.dp),
                    tint = if (enabledPlay) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = onPauseClick,
                enabled = enabledPause
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ico_pause_audio_24),
                    contentDescription = "Pause",
                    modifier = Modifier.size(36.dp),
                    tint = if (enabledPause) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    val menuItems = listOf("Guardar", "Editar", "Eliminar")
//
//    HCItemAudioPlayer(
//        thumbnailPainter = painterResource(id = R.drawable.img_microphone_tag),
//        title = "Título",
//        subtitle = "Texto secundario",
//        menuItems = menuItems,
//        onMenuItemClick = { selectedOption ->
//            println("Selected: $selectedOption")
//        },
//        onPlayClick = { println("Play") },
//        onPauseClick = { println("Pause") },
//        onStopClick = { println("Stop") },
//        progress = 0.5f,
//        currentTime = "00:00",
//        totalTime = "00:00",
//        esPrincipal = true
//    )
//}
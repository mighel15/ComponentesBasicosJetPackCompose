package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha

@Composable
fun HCItemCardExtended(
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
    actionButtons: @Composable RowScope.() -> Unit,
    iconButtons: @Composable RowScope.() -> Unit,
    gridHeaders: List<String> = emptyList(),
    gridData: List<String> = emptyList(),
    gridColumns: Int,
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
                contentDescription = "Avatar de persona",
                size = 60.dp
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                Modifier
                    .weight(4f)
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
                                color = MaterialTheme.colorScheme.primary
                                //color = colorResource(id = R.color.cr_azul)
                            ),
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }

            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        if (gridHeaders.isNotEmpty() || gridData.isNotEmpty()) {

            HCGridData(
                headers = gridHeaders,
                data = gridData,
                columns = gridColumns
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Box(
                Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier.align(Alignment.CenterStart), content = actionButtons)

                Row(modifier = Modifier.align(Alignment.CenterEnd), content = iconButtons)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    val menuItems = listOf("Guardar", "Editar", "Eliminar")
//    val headers = listOf("Nombre", "Edad", "Dirección")
//    val data = listOf("-", "Juan Pérez", "30")
//
//    HCItemCardExtended(
//        thumbnailPainter = painterResource(id = R.drawable.img_user_tag),
//        title = "Título",
//        subtitle = "Texto secundario",
//        menuItems = menuItems,
//        onMenuItemClick = { selectedOption ->
//            println("Selected: $selectedOption")
//        },
//        actionButtons = {
//
//            HCButton(
//                text = "Localizar Ubicacion",
//                icon = painterResource(id = R.drawable.ico_localizacion_24),
//                onClick = { /* Acción 1 */ },
//                enabled = true
//            )
//        },
//        iconButtons = {
//
//        },
//        gridHeaders = headers,
//        gridData = data,
//        gridColumns = 3
//    )
//}
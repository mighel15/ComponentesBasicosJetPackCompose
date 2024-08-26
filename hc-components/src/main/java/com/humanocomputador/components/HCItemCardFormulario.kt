package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HCItemCardFormulario(
    title: String,
    thumbnailPainter: Painter,
    menuItems: List<String>,
    onMenuItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    content: @Composable () -> Unit
) {
    var menuExpandido by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = background, contentColor = contentColor),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = border,
        modifier = modifier //8
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp) //16
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HCCircleIcon(
                    painter = thumbnailPainter,
                    contentDescription = "Icono de evaluación",
                    size = 40.dp
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(10.dp))

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
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHCItemCardFormulario() {
//    val alternativas = listOf("alterno 1", "dsd 2", "Altersdsdnativa 3")
//    var seleccionada by remember { mutableStateOf(alternativas.first()) }
//
//    HCItemCardFormulario(
//        thumbnailPainter = painterResource(id = R.drawable.img_user_tag),
//        title = "Un formulario",
//        menuItems = listOf("Editar", "Eliminar"),
//        onMenuItemClick = {},
//        modifier = Modifier.padding(8.dp),
//        content = {
//            Column {
//                alternativas.forEach { alternativa ->
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            text = alternativa,
//                            style = MaterialTheme.typography.bodyLarge,
//                            modifier = Modifier.weight(1f)
//                        )
//
//                        HCCustomRadioButton(
//                            selected = alternativa == seleccionada,
//                            onClick = { seleccionada = alternativa }
//                        )
//                    }
//                }
//            }
//        }
//    )
}
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
fun HCCardPerfilUsuario(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    imagenPerfil: Painter,
    nombre: String,
    cargo: String,
    onClickCambiarPerfil: () -> Unit,
    onClickCerrarSesion: () -> Unit,
    menuItems: List<String>,
    onMenuItemClick: (String) -> Unit,
    gridHeaders: List<String> = emptyList(),
    gridData: List<String> = emptyList(),
    gridColumns: Int,
    estado: String = "Activo",
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
            HCSquareAvatar(
                painter = imagenPerfil,
                contentDescription = "Avatar de persona",
                size = 80.dp,
                borderColor = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                Modifier
                    .weight(4f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = cargo,
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
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = estado,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.align(Alignment.End)
                    )
                }

            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            if (gridHeaders.isNotEmpty() || gridData.isNotEmpty()) {

                HCGridData(
                    headers = gridHeaders,
                    data = gridData,
                    columns = gridColumns
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                HCButtonSolid(
                    text = "Cambiar Perfil",
                    icon = painterResource(R.drawable.ico_simule_24),
                    onClick = onClickCambiarPerfil,
                    modifier = Modifier.weight(1f),
                    enabled = true
                )

                HCButtonSolid(
                    text = "Cerrar Sesion",
                    icon = painterResource(R.drawable.ico_exit_24),
                    onClick = onClickCerrarSesion,
                    modifier = Modifier.weight(1f),
                    enabled = true
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    val menuItems = listOf("Cambiar Contraseña", "Editar Datos", "Solicitar Soporte")
//    val headers = listOf("Nombre", "Edad", "Dirección")
//    val data = listOf("-", "Juan Pérez", "30")
//
//    HCCardPerfilUsuario(
//        imagenPerfil = painterResource(id = R.drawable.img_foto_perfil_example),
//        nombre = "Título",
//        cargo = "Texto secundario",
//        background = MaterialTheme.colorScheme.background,
//        onClickCambiarPerfil = {},
//        onClickCerrarSesion = {},
//        menuItems = menuItems,
//        onMenuItemClick = { selectedOption ->
//            println("Selected: $selectedOption")
//        },
//        gridHeaders = headers,
//        gridData = data,
//        gridColumns = 1,
//        estado = "Inactivo"
//    )
//}



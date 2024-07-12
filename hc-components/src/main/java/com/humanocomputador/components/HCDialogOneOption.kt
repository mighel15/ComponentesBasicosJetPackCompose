package com.humanocomputador.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HCDialogOneOption(
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    icon: @Composable (() -> Unit)? = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info") },
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Aceptar")
            }
        },
        icon = icon,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = content)
        },
        shape = RoundedCornerShape(12.dp),
//        containerColor = MaterialTheme.colorScheme.secondaryContainer,
//        iconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//        titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//        textContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        tonalElevation = 1.dp,
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCDialogOneOption(
//        title = "Título de prueba",
//        content = "Contenido de prueba",
//        onDismissRequest = {}
//    )
//}
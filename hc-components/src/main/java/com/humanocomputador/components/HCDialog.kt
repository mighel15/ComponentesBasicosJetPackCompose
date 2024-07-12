package com.humanocomputador.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HCDialog(
    title: String,
    content: String,
    confirmButtonText: String,
    dismissButtonText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    icon: @Composable (() -> Unit)? = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info") },
    modifier: Modifier = Modifier
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                openDialog.value = false
                onDismiss()
            },
            icon = icon,
            title = {
                Text(text = title)
            },
            text = {
                Text(text = content)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onConfirm()
                    }
                ) {
                    Text(confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onDismiss()
                    }
                ) {
                    Text(dismissButtonText)
                }
            },
            shape = RoundedCornerShape(12.dp),
//            containerColor = MaterialTheme.colorScheme.secondaryContainer,
//            iconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//            textContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            tonalElevation = 1.dp
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCDialog(
//        title = "Título de prueba",
//        content = "Contenido de prueba",
//        confirmButtonText = "Aceptar",
//        dismissButtonText = "Cancelar",
//        onConfirm = {},
//        onDismiss = {}
//    )
//}
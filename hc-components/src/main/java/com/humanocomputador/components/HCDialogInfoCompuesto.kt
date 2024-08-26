package com.humanocomputador.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HCDialogInfoCompuesto(
    title: String,
    content: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    confirmButtonText: String = "Aceptar",
    confirmButtonAction: () -> Unit = onDismissRequest,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = confirmButtonAction) {
                Text(confirmButtonText)
            }
        },
        title = {
            Text(text = title)
        },
        text = {
            content()
        },
        shape = RoundedCornerShape(12.dp),
//        containerColor = MaterialTheme.colorScheme.secondaryContainer,
//        titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//        textContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        tonalElevation = 1.dp,
        modifier = modifier
            .width(IntrinsicSize.Max)
            .padding(horizontal = 24.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDialogInfoCompuesto(){
//    HCDialogInfoCompuesto(
//        title = "Título de prueba",
//        onDismissRequest = {},
//        content = {
//            Text("Contenido de prueba")
//            Text("Contenido de prueba")
//            Text("Contenido de prueba")
//        },
//    )
}
package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha

@Composable
fun HCTextInput(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    counterMaxLength: Int? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true
) {
    val isError = error != null
    val isCounter = counterMaxLength != null

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (counterMaxLength == null || it.length <= counterMaxLength) {
                    onValueChange(it)
                }
//              CAPITALIZAR LA PRIMER LETRA
//                if (counterMaxLength == null || it.length <= counterMaxLength) {
//                    // Transform the first character to uppercase if it's the first character and not already uppercase
//                    val newValue = if (it.isNotEmpty() && it.length == 1 && it[0].isLowerCase()) {
//                        it.replaceFirstChar { char -> char.uppercaseChar() }
//                    } else {
//                        it
//                    }
//                    onValueChange(newValue)
//                }
            },
            label = { Text(label, maxLines = 1) },
            readOnly = readOnly,
            enabled = enabled,
            singleLine = isSingleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isError) {
                Text(
                    text = error ?: "",
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (isCounter) {
                Text(
                    text = "${value.length}/$counterMaxLength",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
//@Preview (showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTextInput(
//        value = "Este es un valor de prueba",
//        label = "Encabezado",
//        onValueChange = {},
//        //error = "Este campo es requerido",
//    )
//}

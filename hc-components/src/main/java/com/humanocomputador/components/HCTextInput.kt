package com.humanocomputador.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textAlign: TextAlign = TextAlign.Start,
    customLectura: Boolean = true
) {
    val isError = error != null
    val isCounter = counterMaxLength != null

    val backgroundBrush = if (readOnly && customLectura) {
        Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                //Color.Blue.copy(alpha = 0.3f)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                Color.Transparent
            )
        )
    }

//    val backgroundColor = if (readOnly && customLectura) {
//        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f)
//    } else {
//        Color.Transparent
//    }

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
            // para el estilo de la fuente por defecto solo comentar esto
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                textAlign = textAlign
            ),
            visualTransformation = visualTransformation,
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = backgroundBrush)
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
//        error = "Este campo es requerido",
//        isSingleLine = true,
//        keyboardType = KeyboardType.Text,
//        modifier = Modifier,
//        counterMaxLength = 100,
//        readOnly = false,
//        enabled = true,
//        textAlign = TextAlign.Start
//    )
//}

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun HCTextInputMainIcon(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    isSingleLine: Boolean = true,
    modifier: Modifier = Modifier,
    counterMaxLength: Int? = null,
    leadingIcon: @Composable () -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    readOnly: Boolean = false,
    enabled: Boolean = true,
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

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (counterMaxLength == null || it.length <= counterMaxLength) {
                    onValueChange(it)
                }
            },
            label = { Text(label, maxLines = 1) },
            leadingIcon = leadingIcon,
            singleLine = isSingleLine,
            readOnly = readOnly,
            enabled = enabled,
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                textAlign = textAlign
            ),
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
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
                    style = MaterialTheme.typography.bodySmall
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

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTextInputMainIcon(
//        value = "value",
//        label = "label",
//        onValueChange = {},
//        error = "error",
//        isSingleLine = true,
//        modifier = Modifier,
//        counterMaxLength = 10,
//        leadingIcon = {}
//    )
//}
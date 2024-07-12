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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha

@Composable
fun HCTextInputSufijo(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    isSingleLine: Boolean = true,
    suffix: String,
    modifier: Modifier = Modifier,
    counterMaxLength: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Number,
    textAlign: TextAlign = TextAlign.End
) {
    var text by rememberSaveable { mutableStateOf(value) }
    val isError = error != null
    val isCounter = counterMaxLength != null

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (counterMaxLength == null || it.length <= counterMaxLength) {
                    text = it
                    onValueChange(it)
                }
            },
            label = { Text(label, maxLines = 1) },

            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                textAlign = textAlign
            ),
            visualTransformation = SuffixVisualTransformation(suffix),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = isSingleLine,
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
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (isCounter) {
                Text(
                    text = "${text.length}/$counterMaxLength",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

class SuffixVisualTransformation(private val suffix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val spacer = " "
        val transformedText = text + AnnotatedString(spacer + suffix, SpanStyle(Color.Gray))

        return TransformedText(transformedText, SuffixOffsetMapping(text.text))
    }
}

class SuffixOffsetMapping(private val originalText: String) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = offset

    override fun transformedToOriginal(offset: Int): Int {
        return if (offset > originalText.length) originalText.length else offset
    }
}


//@Preview (showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTextInputSufijo(
//        value = "Este es un valor de prueba",
//        label = "Encabezado",
//        onValueChange = {},
//        suffix = "Sufijo",
//        error = "Esto es un error",
//        counterMaxLength = 5
//    )
//}

package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha

@Composable
fun HCTextInputValidador(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    onButtonClick: (String) -> Unit,
    buttonText: String,
    icon: Painter = painterResource(id = R.drawable.ico_check_24),
    error: String? = null,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    counterMaxLength: Int? = null,
    readOnly: Boolean = false
) {
    val isError = error != null
    val isCounter = counterMaxLength != null

    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    if (counterMaxLength == null || it.length <= counterMaxLength) {
                        onValueChange(it)
                    }
                },
                label = { Text(label, maxLines = 1) },
                singleLine = isSingleLine,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                isError = isError,
                readOnly = readOnly,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )

            OutlinedButton(
                onClick = { onButtonClick(value) },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .align(Alignment.Bottom),

                shape = RoundedCornerShape(4.dp)
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(buttonText)
            }
        }

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
//
//@Preview (showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    var text by rememberSaveable { mutableStateOf("") }
//
//    HCTextInputValidador(
//        value = "text",
//        label = "Buscar",
//        onValueChange = { text = it },
//        buttonText = "Buscar",
//        onButtonClick = { enteredText ->
//            println("Texto ingresado: $enteredText")
//        },
//        error = if (text.isEmpty()) "El campo no puede estar vacío" else null,
//        counterMaxLength = 20
//    )
//}

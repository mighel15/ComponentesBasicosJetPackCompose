package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import com.humanocomputador.components.R

@Composable
fun HCTextInputPassword(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    isSingleLine: Boolean = true,
    modifier: Modifier = Modifier,
    counterMaxLength: Int? = null
) {
    var text by rememberSaveable { mutableStateOf(value) }
    var hidden by rememberSaveable { mutableStateOf(true) }
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = isSingleLine,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { hidden = !hidden }) {
                    val vector = painterResource(
                        if (hidden) R.drawable.ico_visibility_off_24 else R.drawable.ico_visibility_on_24
                    )
                    val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña"
                    Icon(painter = vector, contentDescription = description)
                }
            }
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
//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTextInputPassword(
//        value = "123456",
//        label = "Contraseña",
//        onValueChange = {},
//        error = "Error",
//        isSingleLine = true,
//        modifier = Modifier,
//        counterMaxLength = 10
//    )
//}
package com.humanocomputador.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha

@Composable
fun HCTextInputCustom(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    isSingleLine: Boolean = true,
    modifier: Modifier = Modifier,
    helperText: String = "",
    counterMaxLength: Int? = null,
    fontFamily: FontFamily,
    color: Color = Color(0xFF120524),
    shape: Shape = CutCornerShape(ZeroCornerSize)
) {
    //var text by rememberSaveable { mutableStateOf(value) }
    val isCounter = counterMaxLength != null

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (counterMaxLength == null || it.length <= counterMaxLength) {
                    onValueChange(it)
                }
            },
            label = { Text(label, maxLines = 1) },
            singleLine = isSingleLine,
            textStyle = TextStyle(fontFamily = fontFamily),
            colors = TextFieldDefaults.colors(
                focusedTextColor = color,
                focusedLabelColor = color.copy(alpha = ContentAlpha.high),
                focusedIndicatorColor = Color.Transparent,
                cursorColor = color,
            ),
            shape = shape,
            modifier = Modifier.fillMaxWidth().border(1.dp, color)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = helperText,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
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
//    HCTextInputCustom(
//        value = "value",
//        label = "label",
//        onValueChange = {},
//        isSingleLine = true,
//        modifier = Modifier,
//        counterMaxLength = 10
//    )
//}
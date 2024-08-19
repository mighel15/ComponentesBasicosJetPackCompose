package com.humanocomputador.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.humanocomputador.components.R

@Composable
fun HCTextInputOnlyRead(
    value: String,
    label: String,
    isSingleLine: Boolean = false,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icono: Boolean = true
) {
    //var text by rememberSaveable { mutableStateOf(value) }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            label = { Text(label, maxLines = 1) },
            readOnly = true,
            singleLine = isSingleLine,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = if (icono) {
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_lock_24),
                        contentDescription = "description"
                    )
                }
            } else null,
            enabled = enabled
        )
    }
}
//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTextInputOnlyRead(
//        value = "value",
//        label = "label"
//    )
//}
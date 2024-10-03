package com.humanocomputador.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HCComboBox(
    options: List<String>,
    label: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null,
    enabled: Boolean = true,
    enabledSelectable: Boolean = true,
    customLectura: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(if (options.isNotEmpty()) options[0] else "") }
    val isError = error != null

    val backgroundBrush = if (!enabled && customLectura) {
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

    ExposedDropdownMenuBox(
        expanded = expanded && enabled,
        onExpandedChange = { if (enabled) expanded = it },
        modifier = modifier
    ) {
        Column (modifier = Modifier.padding(bottom = 4.dp)) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                singleLine = true,
                label = { Text(label, maxLines = 1) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                isError = isError,
                enabled = enabledSelectable,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(brush = backgroundBrush),
            )
            if (isError) {
                Text(
                    text = error ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }
        }

        if (enabled) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                        onClick = {
                            onOptionSelected(option)
                            text = option
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        //enabled = enabled
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCComboBox(
//        options = listOf("Opción 1", "Opción 2", "Opción 3"),
//        label = "Selecciona una opción",
//        selectedOption = "Opción 1",
//        onOptionSelected = {},
//        //error = "Error de ejemplo"
//    )
//}
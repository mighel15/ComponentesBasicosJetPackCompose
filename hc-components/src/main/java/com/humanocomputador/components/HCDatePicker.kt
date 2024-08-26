package com.humanocomputador.components

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HCDatePicker(
    label: String,
    initialDate: String = "",
    onDateSelected: (String) -> Unit = {},
    error: String? = null,
    modifier: Modifier = Modifier,
    pattern: String = "dd/MM/yyyy"
) {
    var selectedDate by remember { mutableStateOf(initialDate) }
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (selectedDate.isNotBlank()) LocalDate.parse(selectedDate, formatter) else LocalDate.now()

    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val newDate = LocalDate.of(year, month + 1, dayOfMonth).format(formatter)
            selectedDate = newDate
            onDateSelected(newDate)
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
    )

    val isError = error != null

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text(label, maxLines = 1) },
            readOnly = true,
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { datePickerDialog.show() }){
                    Icon(Icons.Filled.DateRange, contentDescription = "Date Picker")
                }
            },
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
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHCDatePicker() {
    HCDatePicker(
        label = "Select Date",
        initialDate = "10/02/1998",
        onDateSelected = {
        },
        error = "Error message",
    )
}


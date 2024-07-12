package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme

@Composable
fun HCSwitch(
    state: Boolean,
    onValueChange: (Boolean) -> Unit,
    text: String,
    enable: Boolean
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            ),
            modifier = Modifier
                .weight(4f)
                .align(Alignment.CenterVertically)
        )
        Switch(
            checked = state,
            onCheckedChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            enabled = enable
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCSwitch(
//        state = true,
//        onValueChange = {},
//        text = "Switch",
//        enable = true
//    )
//}
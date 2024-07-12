package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.MaterialTheme

@Composable
fun HCTitleButton(
    title: String,
    actionButtons: @Composable RowScope.() -> Unit,
    colorText: Color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.title3.copy(
                color = colorText,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End,
            content = actionButtons
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTitleButton(
//        title = "Mi Título",
//        actionButtons = {
//            IconButton(onClick = {  }) {
//                Icon(imageVector = Icons.Default.Person, contentDescription = "Settings")
//            }
//            Button(onClick = {  }) {
//                Text("Acción")
//            }
//        },
//    )
//}
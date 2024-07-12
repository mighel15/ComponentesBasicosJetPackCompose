package com.humanocomputador.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HCList(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier.clickable(onClick = onClick).fillMaxWidth(),
        headlineContent = { Text(text) },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = text
            )
        },
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCList(
//        text = "Item de lista",
//        icon = Icons.Filled.AddCircle,
//        onClick = {}
//    )
//}
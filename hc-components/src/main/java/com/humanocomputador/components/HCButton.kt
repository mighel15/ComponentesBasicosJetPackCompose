package com.humanocomputador.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun HCButton(
    text: String,
    icon: Painter?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(3.dp),
        enabled = enabled
    ) {
        if (icon != null) {
            Icon(painter = icon, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCButton(
//        text = "Botón de prueba",
//        icon = painterResource(id = R.drawable.ico_search_24),
//        onClick = {}
//    )
//}
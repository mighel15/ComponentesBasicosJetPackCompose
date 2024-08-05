package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HCButtonMenuOutlined(
    text: String,
    icon: Painter?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    borderColor: Color = MaterialTheme.colorScheme.primary
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(60.dp),
        shape = RoundedCornerShape(5.dp),
        enabled = enabled,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text)
            if (icon != null) {
                Icon(painter = icon, contentDescription = null)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewButtonMenuOutlined() {
//    HCButtonMenuOutlined(
//        text = "Botón de prueba",
//        icon = painterResource(id = R.drawable.ico_search_24),
//        onClick = {},
//        borderColor = Color.DarkGray
//    )
//}

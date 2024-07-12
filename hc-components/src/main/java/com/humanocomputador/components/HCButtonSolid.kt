package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun HCButtonSolid(
    text: String,
    icon: Painter?,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        colors = colors,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = null
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    maxLines = 1,
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCButtonSolid(
//        text = "Botón de prueba",
//        icon = painterResource(id = R.drawable.ico_search_24),
//        onClick = {},
//        enabled = true,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFF003B73),
//            contentColor = Color.White,
//            disabledContainerColor = Color(0xFFB0BEC5),
//            disabledContentColor = Color.Gray,
//        )
//    )
//}
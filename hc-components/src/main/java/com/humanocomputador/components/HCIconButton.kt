package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun HCIconButton(
    icon: Painter?,
    onClick: () -> Unit,
    description: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            if (icon != null){
                Icon(
                    painter = icon,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = description,
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCIconButton(
//        icon = painterResource(id = R.drawable.ico_check_24),
//        onClick = {},
//        description = "Actualizar imagen",
//    )
//}

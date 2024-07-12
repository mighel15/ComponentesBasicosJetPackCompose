package com.humanocomputador.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HCIconButtonSolid(
    icon: Painter?,
    onClick: () -> Unit,
    description: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#003B73")))
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        colors = colors,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = description,
                tint = Color.White, // El color del icono para que contraste con el fondo azul
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCIconButtonSolid(
//        icon = painterResource(id = R.drawable.ico_check_24),
//        onClick = {},
//        description = "Actualizar imagen",
//    )
//}

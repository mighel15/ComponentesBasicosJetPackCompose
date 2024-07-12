package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HCTitle(
    text: String,
    isBold: Boolean = false,
    isItalic: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    fontSize: Float = 22f,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = color,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    Column(modifier = Modifier.fillMaxSize()) {
//        HCTitle(
//            text = "Título en Negrita y Azul",
//            isBold = true
//        )
//        HCTitle(
//            text = "Título Normal y Rojo",
//            color = colorResource(id = R.color.cr_black),
//            isItalic = true,
//            fontSize = 8f
//        )
//    }
//}
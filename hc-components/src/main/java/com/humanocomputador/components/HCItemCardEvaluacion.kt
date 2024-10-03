package com.humanocomputador.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HCItemCardEvaluacion(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,
    thumbnailPainter: Painter,
    pregunta: String,
    alternativas: List<String>,
    onAlternativaSeleccionada: (String) -> Unit,
    alternativaSeleccionada: String?
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = background, contentColor = contentColor),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = border,
        modifier = modifier //
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp) //16
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HCCircleIcon(
                    painter = thumbnailPainter,
                    contentDescription = "Icono de evaluación",
                    size = 40.dp
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = pregunta,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            alternativas.forEach { alternativa ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onAlternativaSeleccionada(alternativa) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = alternativa,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    HCCustomRadioButton(
                        selected = alternativa == alternativaSeleccionada,
                        onClick = { onAlternativaSeleccionada(alternativa) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemCardEvaluacion() {
//    val alternativas = listOf("Alternativa 1", "Alternativa 2", "Alternativa 3")
//    var seleccionada by remember { mutableStateOf(alternativas.first()) }
//
//    HCItemCardEvaluacion(
//        thumbnailPainter = painterResource(id = R.drawable.img_user_tag),
//        pregunta = "¿Cuál es tu alternativa preferida?",
//        alternativas = alternativas,
//        alternativaSeleccionada = seleccionada,
//        onAlternativaSeleccionada = { seleccionada = it }
//    )
}
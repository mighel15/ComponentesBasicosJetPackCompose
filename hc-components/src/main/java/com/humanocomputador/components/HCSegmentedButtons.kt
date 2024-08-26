package com.humanocomputador.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HCSegmentedButtons(
    segments: List<String>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    defaultSelectedIndex: Int = 0
) {
    var selectedIndex by remember { mutableStateOf(defaultSelectedIndex) }
    Spacer(modifier = Modifier.padding(2.dp))

    if (segments.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay búsquedas recientes",
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
    } else {
        Row(
            modifier = modifier
                .border(1.dp, MaterialTheme.colorScheme.outline, shape = MaterialTheme.shapes.small)
                .padding(2.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            segments.forEachIndexed { index, segment ->
                val isSelected = index == selectedIndex
                val backgroundColor = Color.Transparent
                val textColor = MaterialTheme.colorScheme.primary //MaterialTheme.colorScheme.onSurface

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .clickable {
                            selectedIndex = index
                            onClick(index)
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = segment,
                        color = textColor,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .autoResizeText()
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}

@Composable
fun Modifier.autoResizeText(minTextSize: TextUnit = 12.sp): Modifier {
    return this.then(
        remember {
            TextSizeModifier(minTextSize)
        }
    )
}

private class TextSizeModifier(
    private val minTextSize: TextUnit
) : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(constraints)

        var currentTextSize = 16.sp
        while (placeable.width > constraints.maxWidth && currentTextSize > minTextSize) {
            currentTextSize *= 0.9
        }

        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHCSegmentedButtons() {
    HCSegmentedButtons(
        segments = listOf("Button 1", "Button 2", "Button 3"),
        onClick = { index -> println("Button $index clicked") }
    )
}

package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha

@Composable
fun HCGridData(
    headers: List<String>,
    data: List<String>,
    columns: Int,
    modifier: Modifier = Modifier
) {
    val defaultData = "-"
    val combinedData = headers.zipAll(data, defaultA = defaultData, defaultB = defaultData)

    Column(modifier = modifier) {
        combinedData.chunked(columns).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEachIndexed { index, (header, value) ->
                    Column(
                        modifier = Modifier.weight(1f).padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally //Start
                    ) {
                        Text(
                            text = header,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
                        )
                        Text(
                            text = value,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                    if (index < row.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                        VerticalDivider(
                            modifier = Modifier
                                .height(51.dp)
                                .width(1.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                }

                if (row.size < columns) {
                    repeat(columns - row.size) {
                        Spacer(modifier = Modifier.weight(1f).padding(8.dp))
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 0.dp))
        }
    }
}

private fun <A, B> List<A>.zipAll(
    other: List<B>,
    defaultA: A,
    defaultB: B
): List<Pair<A, B>> {
    val maxSize = maxOf(this.size, other.size)
    return List(maxSize) { index ->
        val first = this.getOrNull(index) ?: defaultA
        val second = other.getOrNull(index) ?: defaultB
        first to second
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCGridData(
//        headers = listOf("Header1", "Header2", "Header3", "Header4", "Header5", "Header6", "Header7", "Header8"),
//        data = listOf("Data1 ", "-", "Data3", "Data4","Data5", "-", "Data7", "Data8"),
//        columns = 3,
//    )
//}
package com.humanocomputador.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HCGridButtons(
    items: List<Pair<Painter, String>>,
    columns: Int,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit = {}
) {
    Column(modifier = modifier.padding(8.dp)) {
        items.chunked(columns).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                    ) {
                        HCButtonSquare(
                            image = item.first,
                            text = item.second,
                            onClick = { onItemClick(index) }
                        )
                    }
                }

                if (row.size < columns) {
                    repeat(columns - row.size) {
                        Spacer(modifier = Modifier.weight(1f).aspectRatio(1f).padding(4.dp))
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewGrid() {
//    val items = listOf(
//        painterResource(id = R.drawable.ico_nuevo_24) to "Image 1",
//        painterResource(id = R.drawable.ico_search_24) to "Image 2",
//        painterResource(id = R.drawable.ico_exit_24) to "Image 3"
//    )
//
//    HCGridButtons(
//        items = items,
//        columns = 3
//    )
//}
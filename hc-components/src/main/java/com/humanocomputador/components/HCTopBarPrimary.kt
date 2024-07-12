package com.humanocomputador.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HCTopBarPrimary(
    title: String,
    navigationIcon: ImageVector = Icons.Filled.Menu,
    navigationIconDescription: String = "Navigate back",
    onNavigationClick: () -> Unit = {},
    actions: List<Pair<ImageVector, String>>,
    onActionClicks: List<() -> Unit>
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(imageVector = navigationIcon, contentDescription = navigationIconDescription)
            }
        },
        title = { Text(text = title) },
        actions = {
            actions.forEachIndexed { index, action ->
                IconButton(onClick = onActionClicks[index]) {
                    Icon(imageVector = action.first, contentDescription = action.second)
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTopBarPrimary(
//        title = "Title",
//        actions = listOf(Icons.Filled.Lock to "Menu", Icons.Filled.AccountCircle to "Menu"),
//        onActionClicks = listOf({}, {})
//    )
//}
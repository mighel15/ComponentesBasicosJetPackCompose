package com.humanocomputador.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HCTab(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = contentColorFor(backgroundColor),
    edgePadding: Dp = 16.dp,
    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit = {
        TabRowDefaults.PrimaryIndicator(
            Modifier.tabIndicatorOffset(it[selectedTabIndex])
        )
    },
    divider: @Composable () -> Unit = {
        HorizontalDivider()
    },
    tabs: @Composable () -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = backgroundColor,
        contentColor = contentColor,
        edgePadding = edgePadding,
        indicator = indicator,
        divider = divider,
        tabs = tabs
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3")
//    var selectedTabIndex by remember { mutableStateOf(0) }
//
//    Column {
//        HCTab(
//            selectedTabIndex = selectedTabIndex,
//            tabs = {
//                tabTitles.forEachIndexed { index, title ->
//                    Tab(
//                        selected = selectedTabIndex == index,
//                        onClick = { selectedTabIndex = index },
//                        text = { Text(title) }
//                    )
//                }
//            }
//        )
//        when (selectedTabIndex) {
//            0 -> Text("Content for Tab 1")
//            1 -> Text("Content for Tab 2")
//            2 -> Text("Content for Tab 3")
//        }
//    }
//}
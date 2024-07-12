package com.humanocomputador.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HCTopBarSecondary(
    title: String,
    navigationIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    navigationIconDescription: String = "Navigate back",
    onNavigationClick: () -> Unit = {},
    actions: List<Pair<Int, String>>,
    onActionClicks: List<() -> Unit>
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconDescription
                )
            }
        },
        title = { Text(text = title) },
        actions = {
            actions.forEachIndexed { index, action ->

                TextButton(
                    onClick = onActionClicks[index]
                ) {
                    Icon(
                        painter = painterResource(id = action.first),
                        contentDescription = action.second,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = action.second,
                    )
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTopBarSecondary(
//        title = "Title",
//        actions = listOf(
//            R.drawable.ico_save_24 to "Guardar"
//        ),
//        onActionClicks = listOf({}, {})
//    )
//}



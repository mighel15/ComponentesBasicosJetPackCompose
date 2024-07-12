package com.humanocomputador.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun HCDialogFullScreen(
    onDismissRequest: () -> Unit,
    title: String,
    content: @Composable () -> Unit,
    actions: List<Pair<Int, String>> = emptyList(),
    onActionClicks: List<() -> Unit> = emptyList()
){
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(0.dp)
            ) {
                HCTopBarSecondary(
                    title = title,
                    navigationIcon = Icons.Default.Close,
                    onNavigationClick = onDismissRequest,
                    actions = actions,
                    onActionClicks = onActionClicks
                )

                HorizontalDivider()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 0.dp)
                ) {
                    content()
                }
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion() {
//    var showPopup by rememberSaveable {
//        mutableStateOf(false)
//    }
//    if (showPopup) {
//        HCDialogFullScreen(
//            onDismissRequest = { showPopup = false },
//            title = "Full Screen Dialog",
//            actions = listOf(
//                R.drawable.ico_save_24 to "Save",
//                R.drawable.ico_save_24 to "Save"
//            ),
//            onActionClicks = listOf({}, {}),
//            content = {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Green)
//                )
//            }
//        )
//    }
//}
package com.humanocomputador.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha

@Composable
fun HCFullScreenPDFDialog(
    onDismissRequest: () -> Unit,
    title: String,
    pageInfo: String,
    uri: Uri,
    onPageChange: (Int) -> Unit,
    iconButtons: @Composable RowScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(0.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(start = 0.dp, end = 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onDismissRequest
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ico_cerrar_24),
                                contentDescription = "Cerrar",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Column {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                            Text(
                                text = pageInfo,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }

                    Row {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Box(
                                Modifier
                                    .padding(horizontal = 6.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    content = iconButtons
                                )
                            }
                        }
                    }
                }

                HCDocumentPDFVisor(
                    uri = uri,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    onPageChange = onPageChange
                )
            }
        }
    }
}
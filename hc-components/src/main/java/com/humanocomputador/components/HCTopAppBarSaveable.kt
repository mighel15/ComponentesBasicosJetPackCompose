package com.humanocomputador.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HCTopAppBarSaveable(
    title: String,
    navController: NavController?,
    navigationIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    navigationIconDescription: String = "Retornar",
    actions: List<Pair<Int, String>>,
    onActionClicks: List<() -> Unit>
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController?.navigateUp() }) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconDescription,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        title = { Text(text = title, color = MaterialTheme.colorScheme.onPrimary) },
        actions = {
            actions.forEachIndexed { index, action ->

                TextButton(
                    onClick = onActionClicks[index]
                ) {
                    Icon(
                        painter = painterResource(id = action.first),
                        contentDescription = action.second,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = action.second,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        //Borrar esto para un fondo blanco
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVisualizacion(){
//    HCTopAppBarSaveable(
//        title = "Title",
//        navController = null,
//        actions = listOf(
//            R.drawable.ico_save_24 to "Guardar"
//        ),
//        onActionClicks = listOf({})
//    )
//}
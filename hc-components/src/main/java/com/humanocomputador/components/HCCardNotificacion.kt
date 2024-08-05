package com.humanocomputador.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HCCardNotificacion(
    name: String,
    text: String = "",
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier.padding(0.dp),
        colors  = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(text = "Notificacion")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                )
                if (expanded) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun HCCardNotificacionPreview() {
//    HCoreXTheme {
//        HCCardNotificacion("Android", "Hello, Android!")
//    }
//}
//
//
//
////MODOS DE USO
//@Composable
//private fun NotificacionesPantalla(
//    modifier: Modifier = Modifier,
//    names: List<String> = List(1000) { "$it" }
//) {
//    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
//        items(items = names) { name ->
//            NotificacionesTema(
//                name = name,
//                text = "Hello, $name!"
//            )
//        }
//    }
//}
//
//@Composable
//private fun NotificacionesTema(name: String, text: String, modifier: Modifier = Modifier) {
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        ),
//        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        HCCardNotificacion(
//            name = name,
//            text = text
//        )
//    }
//}




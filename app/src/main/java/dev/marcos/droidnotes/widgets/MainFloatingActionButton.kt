package dev.marcos.droidnotes.widgets

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import dev.marcos.droidnotes.R

@Composable
fun MainFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        backgroundColor = colorResource(id = R.color.purple_500),
        onClick = { onClick.invoke() },
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
    }
}

package dev.marcos.droidnotes.widgets

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import dev.marcos.droidnotes.utils.OnClick

@Composable
fun MainFloatingActionButton(onClick: OnClick = null) {
    FloatingActionButton(
        onClick = { onClick?.invoke() },
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}
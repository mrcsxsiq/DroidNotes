package dev.marcos.droidnotes.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetWithCloseDialog(
    onClosePressed: () -> Unit,
    modifier: Modifier = Modifier,
    closeButtonColor: Color = Color.Gray,
    content: @Composable() () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Box(modifier.fillMaxWidth()) {
        content()
        IconButton(
            onClick = {
                onClosePressed.invoke()
                focusManager.clearFocus()
            },
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp).size(29.dp)
        ) {
            Icon(Icons.Filled.Close, tint = closeButtonColor, contentDescription = null)
        }
    }
}
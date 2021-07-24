package dev.marcos.droidnotes.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.R
import dev.marcos.droidnotes.ui.theme.quicksandFamily

@Composable
fun Chip(
    selected: Boolean = true,
    text: String,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp),
    selectedIndex: (Int) -> Unit = { },
    index: Int = 0
) {
    Surface(
        color = when {
            selected -> colorResource(id = R.color.purple_500)
            else -> MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity)
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = CircleShape,
        modifier = modifier.clickable  { selectedIndex(index) },
    ) {
       Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            fontFamily = quicksandFamily,
            color = when {
                selected -> Color.White
                else -> Color.Black
            },
            modifier = Modifier.padding(8.dp).requiredWidth(48.dp)
        )

    }
}
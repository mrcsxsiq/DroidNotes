package dev.marcos.droidnotes.widgets

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import dev.marcos.droidnotes.R

@Composable
fun MainTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = colorResource(id = R.color.white)
    )
}

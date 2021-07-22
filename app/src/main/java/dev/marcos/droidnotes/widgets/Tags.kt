package dev.marcos.droidnotes.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.R

@Composable
fun Tags(snackbarVisibleState: MutableState<Boolean>) {
    val listOfTags = listOf(
        stringResource(id = R.string.label_chip_all),
        stringResource(id = R.string.label_chip_todo),
        stringResource(id = R.string.label_chip_doing),
        stringResource(id = R.string.label_chip_done)
    )
    val selected = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            listOfTags.forEachIndexed { index, value ->
                Chip(
                    selected = selected.value == index,
                    text = value,
                    index = 0,
                    selectedIndex = {
                        selected.value = index
                        snackbarVisibleState.value = true
                    }
                )
            }
        }
    }
}
package dev.marcos.droidnotes.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import dev.marcos.droidnotes.R
import dev.marcos.droidnotes.domain.Note
import dev.marcos.droidnotes.domain.getNotesColorsValues
import dev.marcos.droidnotes.domain.getNotesHeightsValues
import dev.marcos.droidnotes.ui.theme.quicksandFamily
import dev.marcos.droidnotes.ui.viewmodel.NotesViewModel
import dev.marcos.droidnotes.widgets.BottomSheetWithCloseDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun SheetLayout(
    viewModel: NotesViewModel,
    onCloseBottomSheet: () -> Unit
) {
    val backgroundColors = listOf(
        R.color.card_color_1,
        R.color.card_color_2,
        R.color.card_color_3,
        R.color.card_color_4,
        R.color.card_color_5,
        R.color.card_color_6
    )

    val openDeleteDialog = remember { mutableStateOf(false) }
    val openWarningDialog = remember { mutableStateOf(false) }

    val title = remember { mutableStateOf(TextFieldValue()) }
    val content = remember { mutableStateOf(TextFieldValue()) }
    val currentColor = remember { mutableStateOf(getNotesColorsValues().random()) }

    val selectedNote by viewModel.selectedNote.observeAsState()
    selectedNote?.let {
        title.value = TextFieldValue(it.title ?: "")
        content.value = TextFieldValue(it.content ?: "")
        currentColor.value = selectedNote?.color ?: getNotesColorsValues().random()
    }

    fun clearInputs() {
        title.value = TextFieldValue()
        content.value = TextFieldValue()
    }

    fun clearScreen() {
        onCloseBottomSheet()
        clearInputs()
        viewModel.selectedNote.value = null
        viewModel.viewModelScope.launch {
            delay(250L)
            currentColor.value = getNotesColorsValues().random()
        }
    }

    if (openDeleteDialog.value) {
        DialogDelete {
            if (it) {
                selectedNote?.let {
                    note ->
                    viewModel.delete(note)
                }
                clearScreen()
            }
            openDeleteDialog.value = false
        }
    }

    if (openWarningDialog.value) {
        DialogWarning {
            openWarningDialog.value = it.not()
        }
    }

    BottomSheetWithCloseDialog(
        onClosePressed = {
            clearScreen()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(400.dp).background(
                colorResource(id = backgroundColors[currentColor.value]), shape = RectangleShape
            )
        ) {
            Column {
                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            stringResource(id = R.string.label_sheet_title),
                            fontFamily = quicksandFamily,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Bold
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                TextField(
                    value = content.value,
                    onValueChange = { content.value = it },
                    singleLine = false,
                    maxLines = 15,
                    placeholder = {
                        Text(
                            stringResource(id = R.string.label_sheet_content),
                            fontFamily = quicksandFamily
                        )
                    },
                    modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Button(
                        onClick = {
                            openDeleteDialog.value = true
                        },
                        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Red),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Icon(Icons.Filled.Delete, tint = Color.White, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = {
                            if (title.value.text.isBlank() or content.value.text.isBlank()) {
                                openWarningDialog.value = true
                            } else {
                                viewModel.insertOrUpdate(
                                    Note(
                                        id = selectedNote?.id ?: 0,
                                        title = title.value.text,
                                        content = content.value.text,
                                        color = currentColor.value,
                                        height = selectedNote?.height
                                            ?: getNotesHeightsValues().random()
                                    )
                                )
                                clearScreen()
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(48.dp)
                    ) {
                        Text(text = stringResource(id = R.string.label_button_save))
                    }
                }
            }
        }
    }
}

@Composable
fun DialogDelete(confirm: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { confirm(false) },
        title = { Text(text = stringResource(id = R.string.dialog_delete_title)) },
        text = { Text(stringResource(id = R.string.dialog_delete_content)) },
        confirmButton = {
            Button(
                onClick = {
                    confirm(true)
                }
            ) {
                Text(stringResource(id = R.string.dialog_delete_confirm_button))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    confirm(false)
                }
            ) {
                Text(stringResource(id = R.string.dialog_delete_dismiss_button))
            }
        }
    )
}

@Composable
fun DialogWarning(confirm: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { confirm(true) },
        title = { Text(text = stringResource(id = R.string.dialog_warning_title)) },
        text = { Text(stringResource(id = R.string.dialog_warning_content)) },
        confirmButton = {
            Button(
                onClick = { confirm(true) }
            ) {
                Text(stringResource(id = R.string.dialog_warning_confirm_button))
            }
        }
    )
}

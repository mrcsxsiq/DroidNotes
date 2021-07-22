package dev.marcos.droidnotes.view.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import dev.marcos.droidnotes.R
import dev.marcos.droidnotes.domain.Note
import dev.marcos.droidnotes.view.theme.quicksandFamily
import dev.marcos.droidnotes.widgets.BottomSheetWithCloseDialog

@Composable
fun SheetLayout(
    onCloseBottomSheet : () -> Unit,
    note: Note? = null
) {

    val title = remember { mutableStateOf(TextFieldValue()) }
    val content = remember { mutableStateOf(TextFieldValue()) }

    BottomSheetWithCloseDialog(onClosePressed = onCloseBottomSheet) {
        Box(
            modifier = Modifier.fillMaxWidth().height(400.dp).background(
                colorResource(id = R.color.card_color_3), shape = RectangleShape
            )
        ) {
            Column {
                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text("Title", fontFamily = quicksandFamily, fontWeight = FontWeight.Bold)
                    },
                    textStyle = TextStyle(
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Bold
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
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
                        Text("Content", fontFamily = quicksandFamily)
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
                            title.value = TextFieldValue()
                            content.value = TextFieldValue()
                        },
                        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Red),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Icon(Icons.Filled.Delete, tint = Color.White, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = { onCloseBottomSheet.invoke() },
                        modifier = Modifier.fillMaxWidth().height(48.dp)
                    ) {
                        Text(text = stringResource(id = R.string.label_button_save))
                    }
                }
            }
        }
    }

}
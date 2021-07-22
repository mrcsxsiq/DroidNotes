package dev.marcos.droidnotes.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.R
import dev.marcos.droidnotes.view.theme.quicksandFamily

@Composable
fun SearchBar(snackbarVisibleState: MutableState<Boolean>) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        val focusManager = LocalFocusManager.current
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            keyboardOptions = KeyboardOptions(imeAction = Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    snackbarVisibleState.value = true
                    focusManager.clearFocus()
                    textState.value = TextFieldValue()
                }
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            trailingIcon = { Icon(Icons.Filled.Search, null) },
            maxLines = 1,
            placeholder = {
                Text(stringResource(id = R.string.label_search), fontFamily = quicksandFamily)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

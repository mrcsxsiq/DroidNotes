package dev.marcos.droidnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.view.theme.quicksandFamily
import dev.marcos.droidnotes.view.viewmodel.NotesViewModel
import dev.marcos.droidnotes.widgets.BottomSheetWithCloseDialog
import dev.marcos.droidnotes.widgets.MainFloatingActionButton
import dev.marcos.droidnotes.widgets.MainTopBar
import dev.marcos.droidnotes.widgets.SearchBar
import dev.marcos.droidnotes.widgets.StaggeredVerticalGrid
import dev.marcos.droidnotes.widgets.Tags
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: NotesViewModel

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        viewModel = ViewModelProvider(this, Injector.provideNotesViewModelFactory()).get(
//            NotesViewModel::class.java)

        setContent {
            MainContent()
        }
    }

    @ExperimentalMaterialApi
    @Preview
    @Composable
    fun MainContent() {

        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState()

        val closeSheet: () -> Unit = {
            scope.launch {
                scaffoldState.bottomSheetState.collapse()
            }
        }

        val openSheet: () -> Unit = {
            scope.launch {
                scaffoldState.bottomSheetState.expand()
            }
        }

        BottomSheetScaffold(
            sheetPeekHeight = 0.dp,
            scaffoldState = scaffoldState,
            sheetShape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            sheetContent = {
                SheetLayout(closeSheet)
            }) { paddingValues ->
            Box(Modifier.padding(paddingValues)){
                MainLayout(openSheet)
            }
        }
    }
}

@Composable
fun MainLayout(openSheet: () -> Unit) {

    val snackbarVisibleState = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val channel = remember { Channel<Int>(Channel.Factory.CONFLATED) }

    if (snackbarVisibleState.value) {
        LaunchedEffect(channel) {
            snackbarHostState.showSnackbar(
                message = "Not implemented yet :)",
                duration = SnackbarDuration.Short
            ).also {
                snackbarVisibleState.value = false
            }
        }
    }

    Box {
        Scaffold(
            topBar = { MainTopBar() },
            content = {
                Column {
                    SearchBar(snackbarVisibleState)
                    Tags(snackbarVisibleState)
                    LazyColumn {
                        item {
                            StaggeredVerticalGrid(
                                maxColumnWidth = 340.dp,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                for (i in 0 until (8..19).random()) {
                                    val h = listOf(180, 145, 200, 240).random().dp
                                    Card(
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier
                                            .height(h)
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        elevation = 2.dp,
                                        backgroundColor = colorResource(
                                            id = listOf(
                                                R.color.card_color_1,
                                                R.color.card_color_2,
                                                R.color.card_color_3,
                                                R.color.card_color_4,
                                                R.color.card_color_5,
                                                R.color.card_color_6
                                            ).random()
                                        ),
                                    ) {
                                        Column {
                                            Text(
                                                text = "Title",
                                                fontFamily = quicksandFamily,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                            Text(
                                                text = "Content content content content content content content content",
                                                fontStyle = FontStyle.Italic,
                                                fontFamily = quicksandFamily,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.padding(horizontal = 8.dp)
                                            )
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            },
            floatingActionButton = { MainFloatingActionButton { openSheet() } }
        )
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun SheetLayout(onCloseBottomSheet :()->Unit) {

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
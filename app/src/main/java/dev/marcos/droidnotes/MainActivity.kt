package dev.marcos.droidnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.ui.view.ListLayout
import dev.marcos.droidnotes.ui.view.SheetLayout
import dev.marcos.droidnotes.ui.viewmodel.NotesViewModel
import dev.marcos.droidnotes.ui.viewmodel.NotesViewModelFactory
import dev.marcos.droidnotes.widgets.MainFloatingActionButton
import dev.marcos.droidnotes.widgets.MainTopBar
import dev.marcos.droidnotes.widgets.SearchBar
import dev.marcos.droidnotes.widgets.Tags
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((application as NotesApplication).repository)
    }

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(viewModel)
        }
    }

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    @Composable
    fun MainContent(viewModel: NotesViewModel) {

        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState()

        val snackbarVisibleState = remember { mutableStateOf(false) }
        val snackbarHostState = remember { SnackbarHostState() }
        val channel = remember { Channel<Int>(Channel.Factory.CONFLATED) }

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

        if (snackbarVisibleState.value) {
            LaunchedEffect(channel) {
                snackbarHostState.showSnackbar(
                    message = "Not implemented yet   :)",
                    duration = SnackbarDuration.Short
                ).also {
                    snackbarVisibleState.value = false
                }
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
                SheetLayout(viewModel) { closeSheet() }
            }
        ) { paddingValues ->
            Box(Modifier.padding(paddingValues)) {
                Scaffold(
                    topBar = { MainTopBar() },
                    content = {
                        Column {
                            SearchBar(snackbarVisibleState)
                            Tags(snackbarVisibleState)
                            ListLayout(viewModel) { openSheet() }
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
    }
}

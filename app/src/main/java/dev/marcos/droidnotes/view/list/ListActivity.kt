package dev.marcos.droidnotes.view.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import dev.marcos.droidnotes.di.Injector
import dev.marcos.droidnotes.view.list.viewmodel.NotesViewModel
import dev.marcos.droidnotes.widgets.MainFloatingActionButton
import dev.marcos.droidnotes.widgets.MainTopBar

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, Injector.provideNotesViewModelFactory()).get(
            NotesViewModel::class.java)

        setContent {
            MainContent()
        }
    }

}

@Preview
@Composable
fun MainContent() {
    Scaffold(
        topBar = { MainTopBar() },
        floatingActionButton = { MainFloatingActionButton() }
    ) {

    }
}


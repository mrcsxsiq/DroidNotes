package dev.marcos.droidnotes.view.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.view.list.viewmodel.NotesViewModel
import dev.marcos.droidnotes.widgets.MainFloatingActionButton
import dev.marcos.droidnotes.widgets.MainTopBar
import dev.marcos.droidnotes.widgets.SearchBar
import dev.marcos.droidnotes.widgets.StaggeredVerticalGrid
import dev.marcos.droidnotes.widgets.Tags

class ListActivity : ComponentActivity() {

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        viewModel = ViewModelProvider(this, Injector.provideNotesViewModelFactory()).get(
//            NotesViewModel::class.java)

        setContent {
            MainContent()
        }
    }

    @Preview
    @Composable
    fun MainContent() {
         Scaffold(
            topBar = { MainTopBar() },
            content = {
                Column {
                    SearchBar("Search")
                    Tags(listOf("All", "Todo", "Doing", "Done"))
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
                                        modifier = Modifier.height(h).fillMaxWidth().padding(8.dp),
                                        elevation = 12.dp,
                                        backgroundColor = listOf(Color.White).random(),
                                    ) {
                                        Text(
                                            text = "Jetpack Compose",
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            floatingActionButton = { MainFloatingActionButton() }
        )
    }
}


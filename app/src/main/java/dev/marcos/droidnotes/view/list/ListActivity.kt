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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.R
import dev.marcos.droidnotes.view.list.viewmodel.NotesViewModel
import dev.marcos.droidnotes.widgets.Chip
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
                                        modifier = Modifier
                                            .height(h)
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        elevation = 2.dp,
                                        backgroundColor = colorResource(id = listOf(R.color.card_color_1, R.color.card_color_2, R.color.card_color_3, R.color.card_color_4, R.color.card_color_5, R.color.card_color_6).random()),
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
            floatingActionButton = { MainFloatingActionButton() }
        )
    }
}

val quicksandFamily = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_regular, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)

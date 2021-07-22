package dev.marcos.droidnotes.view.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.marcos.droidnotes.R
import dev.marcos.droidnotes.view.theme.quicksandFamily
import dev.marcos.droidnotes.view.viewmodel.NotesViewModel
import dev.marcos.droidnotes.widgets.StaggeredVerticalGrid

@ExperimentalMaterialApi
@Composable
fun ListLayout(
    viewModel: NotesViewModel,
    openSheet: () -> Unit
) {
    val items by viewModel.notes.observeAsState()

    val backgroundColors = listOf(
        R.color.card_color_1,
        R.color.card_color_2,
        R.color.card_color_3,
        R.color.card_color_4,
        R.color.card_color_5,
        R.color.card_color_6
    )

    LazyColumn {
        item {
            StaggeredVerticalGrid(
                maxColumnWidth = 340.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                items?.forEach{
                    Card(
                        onClick = {
                            openSheet.invoke()
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .height(it.height.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 2.dp,
                        backgroundColor = colorResource(id = backgroundColors[it.color]),
                    ) {
                        Column {
                            Text(
                                text = it.title ?: "",
                                fontFamily = quicksandFamily,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                text = it.content ?: "",
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
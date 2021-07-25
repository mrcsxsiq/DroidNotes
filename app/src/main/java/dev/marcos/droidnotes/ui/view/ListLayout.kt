package dev.marcos.droidnotes.ui.view

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dev.marcos.droidnotes.R
import dev.marcos.droidnotes.domain.getNotesColorsValues
import dev.marcos.droidnotes.ui.theme.quicksandFamily
import dev.marcos.droidnotes.ui.viewmodel.NotesViewModel
import dev.marcos.droidnotes.widgets.StaggeredVerticalGrid
import kotlinx.coroutines.delay

@ExperimentalMaterialApi
@Composable
fun ListLayout(
    viewModel: NotesViewModel,
    openSheet: () -> Unit
) {

    val isLoading = remember { mutableStateOf(true) }

    val items by viewModel.notes.observeAsState()

    val backgroundColors = listOf(
        R.color.card_color_1,
        R.color.card_color_2,
        R.color.card_color_3,
        R.color.card_color_4,
        R.color.card_color_5,
        R.color.card_color_6
    )

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }

    val positionAnimation = remember { Animatable(0f) }
    LaunchedEffect(positionAnimation) {
        delay(1000)
        isLoading.value = false
    }

    if (isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(refreshing),
            onRefresh = { refreshing = true },
        ) {
            if (items?.size ?: 0 == 0) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.height(175.dp).width(175.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.empty_state),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.empty_list),
                        fontFamily = quicksandFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(72.dp))
                }
            } else {
                LazyColumn {
                    item {
                        StaggeredVerticalGrid(
                            maxColumnWidth = 340.dp,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            items?.forEach {
                                Card(
                                    onClick = {
                                        viewModel.selectedNote.value = it
                                        openSheet.invoke()
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.height(it.height?.dp ?: 200.dp).fillMaxWidth()
                                        .padding(8.dp),
                                    elevation = 2.dp,
                                    backgroundColor = colorResource(
                                        id = backgroundColors[
                                            it.color
                                                ?: getNotesColorsValues().random()
                                        ]
                                    ),
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
        }
    }
}

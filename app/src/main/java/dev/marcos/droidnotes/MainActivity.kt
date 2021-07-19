package dev.marcos.droidnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
//                    Greeting("Android")

        }
    }

    private fun addNote() {

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

@Composable
fun MainTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = colorResource(id = R.color.white)
    )
}

@Composable
fun MainFloatingActionButton(onClick: (() -> Unit)? = null) {
    FloatingActionButton(
        onClick = { onClick?.invoke() },
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
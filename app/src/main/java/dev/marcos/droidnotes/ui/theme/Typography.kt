package dev.marcos.droidnotes.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import dev.marcos.droidnotes.R

val quicksandFamily = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_regular, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)

val caveatFamily = FontFamily(
    Font(R.font.caveat_regular, FontWeight.Medium),
    Font(R.font.caveat_bold, FontWeight.Bold)
)

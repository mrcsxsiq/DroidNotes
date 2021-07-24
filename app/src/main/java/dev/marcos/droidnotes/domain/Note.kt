package dev.marcos.droidnotes.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "content")
    val content: String?,

    @ColumnInfo(name = "color")
    val color: Int? = 0,

    @ColumnInfo(name = "height")
    val height: Int? = 200
)


fun getNotesHeightsValues() = listOf(180, 145, 200, 240)

fun getNotesColorsValues() = (0..5)
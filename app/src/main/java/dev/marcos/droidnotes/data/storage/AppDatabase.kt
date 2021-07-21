package dev.marcos.droidnotes.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.marcos.droidnotes.domain.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): NotesDao
}

package dev.marcos.droidnotes

import android.app.Application
import dev.marcos.droidnotes.data.NotesDataSource
import dev.marcos.droidnotes.data.NotesRepository
import dev.marcos.droidnotes.data.storage.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }

    private val dataSource by lazy {
        NotesDataSource(
            noteDao = database.userDao(),
            service = null
        )
    }

    val repository by lazy {
        NotesRepository(  dataSource )
    }

}
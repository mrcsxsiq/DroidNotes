package dev.marcos.droidnotes

import android.app.Application
import dev.marcos.droidnotes.data.NotesDataSource
import dev.marcos.droidnotes.data.NotesRepository
import dev.marcos.droidnotes.data.storage.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.sql.DataSource

class NotesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }

    val dataSource by lazy {
        NotesDataSource(
            noteDao = database.userDao(),
            service = null
        )
    }
    val repository by lazy {
        NotesRepository(  dataSource )
    }

}
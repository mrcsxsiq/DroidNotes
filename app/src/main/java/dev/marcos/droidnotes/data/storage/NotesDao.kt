package dev.marcos.droidnotes.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcos.droidnotes.domain.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM Notes WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    fun delete(user: Note)

    @Delete
    fun deleteAll(vararg users: Note)
}
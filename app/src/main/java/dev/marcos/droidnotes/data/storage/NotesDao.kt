package dev.marcos.droidnotes.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.marcos.droidnotes.domain.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getAll(): List<Note>

    @Query("SELECT * FROM Notes WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Note>

    @Insert
    fun insertAll(vararg users: Note)

    @Delete
    fun delete(user: Note)
}
package dev.marcos.droidnotes.data

import dev.marcos.droidnotes.domain.Note

class NotesRepository(private val dataSource: NotesDataSource) {
    fun list(note: Note) = dataSource.list(note)
    fun get(note: Note) = dataSource.get(note)
    fun insert(note: Note) = dataSource.insert(note)
    fun update(note: Note) = dataSource.update(note)
    fun delete(note: Note) = dataSource.delete(note)
}
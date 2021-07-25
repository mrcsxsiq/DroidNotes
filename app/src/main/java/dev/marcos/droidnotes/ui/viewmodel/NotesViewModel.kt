package dev.marcos.droidnotes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.marcos.droidnotes.data.NotesRepository
import dev.marcos.droidnotes.domain.Note
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository
) : ViewModel() {

    val notes: LiveData<List<Note>> = repository.list().asLiveData()

    val selectedNote: MutableLiveData<Note?> by lazy { MutableLiveData<Note?>() }

    private fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    private fun update(note: Note) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }

    fun insertOrUpdate(note: Note) {
        if (note.id == 0) {
            insert(note = note)
        } else {
            update(note = note)
        }
    }
}

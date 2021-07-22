package dev.marcos.droidnotes.view.viewmodel

import androidx.lifecycle.LiveData
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

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }
}
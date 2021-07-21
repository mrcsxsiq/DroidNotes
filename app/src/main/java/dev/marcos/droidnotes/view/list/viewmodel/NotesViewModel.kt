package dev.marcos.droidnotes.view.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcos.droidnotes.data.NotesRepository
import dev.marcos.droidnotes.proto.Note
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository
) : ViewModel() {

    val notes: MutableLiveData<Note>
        get() = repository.list().notesLis

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }
}
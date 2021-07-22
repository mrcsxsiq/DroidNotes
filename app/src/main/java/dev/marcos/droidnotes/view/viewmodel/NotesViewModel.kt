package dev.marcos.droidnotes.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcos.droidnotes.data.NotesRepository
import dev.marcos.droidnotes.domain.Note
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository
) : ViewModel() {

    val notes: MutableLiveData<MutableList<Note>>
        get() = mutableListOf<Note>() as MutableLiveData<MutableList<Note>>

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }
}
package dev.marcos.droidnotes.view.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.marcos.droidnotes.data.NotesRepository

class NotesViewModelFactory(
    private val repository: NotesRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(repository) as T
    }
}
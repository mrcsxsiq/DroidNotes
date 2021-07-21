package dev.marcos.droidnotes.di

import dev.marcos.droidnotes.data.NotesDataSource
import dev.marcos.droidnotes.data.NotesRepository
import dev.marcos.droidnotes.data.network.GrpcService
import dev.marcos.droidnotes.view.list.viewmodel.NotesViewModelFactory

object Injector {

    private fun provideGrpcService() = GrpcService(
        host = "192.168.1.107",
        port = 50051
    )

    private fun provideNotesRemoteDataSource(service: GrpcService) = NotesDataSource(service)

    private fun provideNotesRepository(dataSource: NotesDataSource) = NotesRepository(dataSource)

    fun provideNotesViewModelFactory() = NotesViewModelFactory(
        provideNotesRepository(
            provideNotesRemoteDataSource(
                provideGrpcService()
            )
        )
    )
}
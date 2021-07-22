package dev.marcos.droidnotes.data

import dev.marcos.droidnotes.data.network.GrpcService
import dev.marcos.droidnotes.data.storage.NotesDao
import dev.marcos.droidnotes.domain.Note
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow

class NotesDataSource(
    private val noteDao: NotesDao,
    private val service: GrpcService?
) {

    private lateinit var channel: ManagedChannel

    fun list(): Flow<List<Note>> {
        return noteDao.getAll()
    }

    fun get(note: Note) {

    }

    fun insert(note: Note) {

    }

    fun update(note: Note) {

    }

    fun delete(note: Note) {

    }

    suspend fun transfer(note: Note?) {
//        withContext(Dispatchers.IO) {
//            channel = service.createManagedChannel()
//
//            val stub = NoteServiceGrpc.newBlockingStub(channel)
//
//            val request = note?.toRequest()
//
//            return@withContext try {
//                stub.insert(request)
//            } catch (e: StatusRuntimeException) {
//                e.printStackTrace()
//                e.status.code.toString() + "\n" + e.status.cause.toString()
//            } finally {
//                channel.shutdown()
//            }
//        }
    }
}
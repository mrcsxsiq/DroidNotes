package dev.marcos.droidnotes.data

import dev.marcos.droidnotes.data.network.GrpcService
import dev.marcos.droidnotes.domain.Note
import io.grpc.ManagedChannel
import io.grpc.StatusRuntimeException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesDataSource(private val service: GrpcService) {

    private lateinit var channel: ManagedChannel

    fun list(note: Note) {

    }

    fun get(note: Note)  {

    }

    fun insert(note: Note)  {

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
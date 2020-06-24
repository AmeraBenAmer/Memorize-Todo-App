package com.engamsba.mylife.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.engamsba.mylife.data.ALLDatabase
import com.engamsba.mylife.data.dao.NoteDataBaseDao
import com.engamsba.mylife.data.model.Note

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(application: Application, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO){

    private val noteDao: NoteDataBaseDao
    val allNotes: LiveData<List<Note>>

    init {
        val noteDatabase = ALLDatabase.getInstance(application)
        noteDao = noteDatabase.noteDataBaseDao

        allNotes = noteDao.allNotes
    }

    suspend fun insertOrUpdate(note: Note) = withContext(ioDispatcher){
        noteDao.insertOrUpdateNote(note)
    }

    suspend fun findNote(query: String) = withContext(ioDispatcher){
        return@withContext noteDao.getNotesForSearch(query)
    }

    suspend fun delete(note:Note) = withContext(ioDispatcher){
        noteDao.deleteNote(note)
    }

}

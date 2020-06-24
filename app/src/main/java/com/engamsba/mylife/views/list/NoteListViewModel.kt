package com.engamsba.mylife.views.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.engamsba.mylife.data.model.Note
import com.engamsba.mylife.data.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteListViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val repository: NoteRepository =
        NoteRepository(application)
    val allNotes: LiveData<List<Note>>

    init {
        allNotes = repository.allNotes
    }


    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun findNote(query: String){
        uiScope.launch{
            repository.findNote(query)

        }
    }
}
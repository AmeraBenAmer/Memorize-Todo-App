package com.engamsba.mylife.views.add

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.engamsba.mylife.data.model.Note
import com.engamsba.mylife.data.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class InsertNoteViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository :NoteRepository = NoteRepository(application)
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

     fun addNote(note: Note){
         uiScope.launch{
             repository.insertOrUpdate(note)

         }
    }
}
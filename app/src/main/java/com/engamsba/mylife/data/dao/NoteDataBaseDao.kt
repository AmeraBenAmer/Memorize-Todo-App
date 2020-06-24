package com.engamsba.mylife.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.engamsba.mylife.data.model.Note


@Dao
interface NoteDataBaseDao {

    @Query("SELECT * FROM notes")
     fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
     fun getNoteById(id: Long): Note

    @Delete
     fun deleteNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertOrUpdateNote(note: Note)

    @get:Query("SELECT * FROM notes ORDER BY id DESC")
    val allNotes: LiveData<List<Note>>


    @Query("SELECT * FROM notes WHERE title_note LIKE '%' || :searchText || '%' || categories LIKE '%' || :searchText || '%'")
    fun getNotesForSearch(searchText: String): LiveData<List<Note>>

}
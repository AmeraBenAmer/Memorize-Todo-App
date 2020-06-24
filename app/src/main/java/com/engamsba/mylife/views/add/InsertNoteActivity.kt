package com.engamsba.mylife.views.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.engamsba.mylife.R
import com.engamsba.mylife.data.model.Note
import com.engamsba.mylife.helper.toast
import kotlinx.android.synthetic.main.activity_insert_edit_note.*
import java.text.SimpleDateFormat
import java.util.*

class InsertNoteActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var viewModel: InsertNoteViewModel
    lateinit var categoryNote :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_edit_note)

         viewModel = ViewModelProviders.of(this).get(InsertNoteViewModel::class.java)

        initSpinner()

         btn_insert_note.setOnClickListener {
            val titleNote = title_note.text.toString().trim()
            val descNote = desc_note.text.toString().trim()

            if (titleNote.isEmpty()){
                title_note.error = "title is required"
                title_note.requestFocus()
                return@setOnClickListener
            }

            if (descNote.isEmpty()){
                desc_note.error = "description is required"
                desc_note.requestFocus()
                return@setOnClickListener
            }

//            launch {
                val note = Note(
                    titleNote,
                    descNote,
                    getCalendarTime(),
                    "",
                    categoryNote
                )

             viewModel.addNote(note)
             Log.i("saved", "insert data scessess")
             application.toast("Note Saved")



//                baseContext?.let {
//                    ALLDatabase(it).getNoteDatabaseDao().insertOrUpdateNote(note)
//
//                    application.toast("Note Saved")
//                }

//                viewModel.saveTask(note)








        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCalendarTime(): String {
        val cal = Calendar.getInstance(TimeZone.getDefault())
        val format = SimpleDateFormat("d-MMM-yyyy | HH:mm:ss")
        format.timeZone = cal.timeZone
        return format.format(cal.time)
    }

    private fun initSpinner(){
        val spinner = categories_note
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {

        categoryNote = parent!!.getItemAtPosition(pos).toString()

    }

//     private fun createViewModel() = ViewModelProviders.of(this).get(NoteViewModel::class.java)

}
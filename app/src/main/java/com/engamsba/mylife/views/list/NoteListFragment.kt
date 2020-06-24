package com.engamsba.mylife.views.list

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.room.InvalidationTracker
import com.engamsba.mylife.R
import com.engamsba.mylife.data.model.Note
import com.engamsba.mylife.views.add.InsertNoteActivity
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlinx.android.synthetic.main.fragment_note_list.view.*
import kotlinx.android.synthetic.main.fragment_note_list.view.notes_recycle_view


class NoteListFragment : Fragment(),
    NoteListAdapter.OnItemClickListener,
        SearchView.OnQueryTextListener,
        SearchView.OnCloseListener
{


    private lateinit var searchView: SearchView

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(NoteListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_note_list, container, false)

        view.fab_add_note.setOnClickListener {
            val intent = Intent(activity, InsertNoteActivity::class.java)
            startActivity(intent)
        }

        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allNotes.observe(this, Observer {

            notes_recycle_view.adapter = NoteListAdapter(it, this)
        })

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note_list, menu)
        searchView = menu.findItem(R.id.menu_search)?.actionView as SearchView

        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    override fun onItemClick(note: Note, itemView: View) {
        val noteBundle = Bundle().apply { putInt(getString(R.string.note_id), note.id.toInt()) }
        view?.findNavController()
            ?.navigate(R.id.action_noteListFragment_to_detailsNoteFragment, noteBundle)

    }

    override fun onQueryTextSubmit(pquery: String?): Boolean  = true

    override fun onQueryTextChange(query: String?): Boolean {
        viewModel.findNote(query!!)
        return true
    }

    override fun onClose(): Boolean {
        viewModel.allNotes
        searchView.onActionViewCollapsed()
        return true
    }


}
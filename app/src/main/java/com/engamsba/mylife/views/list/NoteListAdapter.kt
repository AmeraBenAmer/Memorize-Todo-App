package com.engamsba.mylife.views.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.engamsba.mylife.R
import com.engamsba.mylife.data.model.Note
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class NoteListAdapter(
    private val items: List<Note>,
    private val clickListener:  OnItemClickListener
) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(note: Note, itemView: View)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position], clickListener)
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(note: Note, listener:  OnItemClickListener) = with(itemView){
            title_note_rv.text = note.titleNote
            detail_note_rv.text = note.descNote
            time_note_rv.text = note.creationDate


            setOnClickListener {view->
                listener.onItemClick(note, view)
            }
        }

    }
}
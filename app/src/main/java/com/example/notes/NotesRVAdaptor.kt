package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdaptor(private val context: Context, private val listener: INotesRVAdaptor):RecyclerView.Adapter<NotesRVAdaptor.NotesViewHolder>(){
    private val allNotes=ArrayList<Note>()
     inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textView=itemView.findViewById<TextView>(R.id.tv1)
        val delete=itemView.findViewById<ImageView>(R.id.delete_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val viewHolder=NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.delete.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()

    }

}
interface INotesRVAdaptor
{
    fun onItemClicked(note:Note)
}
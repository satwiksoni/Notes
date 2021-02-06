package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdaptor {
    lateinit var viewModel:NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
        getInstance(application)).get(NoteViewModel::class.java)


        rv1.layoutManager=LinearLayoutManager(this)
        val adaptor=NotesRVAdaptor(this,this)
        rv1.adapter=adaptor


        viewModel.allNotes.observe(this, Observer
        {list->
            list?.let {
                adaptor.updateList(it)
                      }

        })


    }


    override fun onItemClicked(note: Note) {
        val dialog=AlertDialog.Builder(this)
        dialog.setTitle("Confermation")
        var temp=0
        dialog.setCancelable(false)
        dialog.setMessage("Are you sure want to delete ?")
        dialog.setPositiveButton("Yes") {dialogInterface , which ->  viewModel.delete(note)
            Toast.makeText(this,"Deleted Successfully  : )",Toast.LENGTH_LONG).show()
        }
        dialog.setNeutralButton("Cancel"){dialogInterface , which ->
            Toast.makeText(this,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
        }
        dialog.setNegativeButton("No"){dialogInterface,which->
            Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show()
        }

        dialog.show()

       }

    fun Insert(view: View) {
        val text=et1.text.toString()
        if(text.isNotEmpty())
        viewModel.insert(Note(text))
        Toast.makeText(this,"Added Successfully  : )",Toast.LENGTH_LONG).show()
    }
}
package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){
    val allNotes:LiveData<List<Note>>
    val repo:NoteRepo
     init
         {
             val dao = NoteDatabase.getDatabase(application).getNodeDao()
              repo=NoteRepo(dao)
             allNotes=repo.allNotes
         }
    fun delete(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }

    fun insert(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }
 }
package com.example.contactsproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactsproject.Contact
import com.example.contactsproject.ContactRepository

class MainViewModel (application: Application) : AndroidViewModel(application){

    private val repository : ContactRepository = ContactRepository(application)
    private val allContacts : LiveData<List<Contact>>?
    private val searchResults : MutableLiveData<List<Contact>>

    init{
        allContacts = repository.allContacts
        searchResults = repository.searchResults
    }

    fun insertContact(newcontact: Contact){
        repository.insertContact(newcontact)
    }
    fun findContact(name: String){
        repository.findContact(name)
    }
    fun deleteContact(contact: String){
        repository.deleteContact(contact)
    }
    fun getSearchResults(): MutableLiveData<List<Contact>>{
        return searchResults
    }
    fun getAllContacts(): LiveData<List<Contact>>?{
        return allContacts
    }
}
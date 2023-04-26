package com.example.contactsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.contactsproject.Contact
import com.example.contactsproject.R

class ContactListAdapter(private val contactItemLayout: Int) :
                RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    private var contactList: List<Contact>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val item = holder.item
        contactList.let{
            item.text = it!![listPosition].enterName
        }
    }
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(contactItemLayout, parent, false)
        return ViewHolder(view)
    }
    fun setContactList(contacts: List<Contact>){
        contactList = contacts
        notifyDataSetChanged()
    }
    override fun getItmeCount(): Int {
        return if (contactList == null) 0 else contactList!!.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.contact_row)
    }
}
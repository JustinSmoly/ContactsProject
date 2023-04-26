package com.example.contactsproject.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactsproject.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsproject.Contact
import androidx.fragment.app.viewModels
import com.example.contactsproject.ContactListAdapter
import java.util.*
import com.example.contactsproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var adapter: ContactListAdapter? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listenerSetup()
        observerSetup()
        recyclersetup()
    }

    private fun clearFields() {
        binding.ContactID.text = ""
        binding.inputName.setText("")
        binding.inputPhone.setText("")
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val name = binding.inputName.text.toString()
            val phone = binding.inputPhone.text.toString()

            if (name != "" && phone != "") {
                val contact = Contact(name, Integer.parseInt(phone))
                viewModel.insertContact(contact)
                clearFields()
            } else {
                binding.ContactID.text = "Incomplete information"
            }
        }
        binding.findButton.setOnClickListener { viewModel.findContact(binding.inputName.text.toString()) }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteContact(binding.inputName.text.toString())
            clearFields()
        }
    }

    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(this, Observer { contacts ->
            contacts?.let { adapter?.setContactList(it) }
        })
        viewModel.getSearchResults().observe(this, Observer { contacts ->
            contacts?.let {
                if (it.isNotEmpty()) {
                    binding.ContactID.text = String.format(Locale.US, "%d", it[0].id)
                    binding.inputName.setText(it[0].enterName)
                    binding.inputPhone.setText(String.format(Locale.US, "%d", it[0].enterPhone))
                } else {
                    binding.ContactID.text = "No Match"
                }
            }
        })
    }
    private fun recyclersetup(){
        adapter = ContactListAdapter(R.layout.card_layout)
        binding.infoRecycler.layoutManager = LinearLayoutManager(context)
        binding.infoRecycler.adapter = adapter
    }
}


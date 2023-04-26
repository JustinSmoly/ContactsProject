package com.example.contactsproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.contactsproject.Contact
import com.example.contactsproject.ContactDao

@Database(entities = [(Contact::class)], version = 1)
abstract class ContactRoomDataBase: RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object{

        private var INSTANCE: ContactRoomDataBase? = null

        internal fun getDatabase(context: Context): ContactRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(ContactRoomDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<ContactRoomDataBase>(
                            context.applicationContext,
                            ContactRoomDataBase::class.java, "contact_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
            }
        }
    }
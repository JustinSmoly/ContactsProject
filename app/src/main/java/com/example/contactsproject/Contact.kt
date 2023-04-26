package com.example.contactsproject

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
class Contact {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "enterName")
    var enterName: String? = null
    var enterPhone: Int = 0

    constructor(){}

    constructor(id : Int, enterName : String, enterPhone : Int){
        this.id = id
        this.enterName = enterName
        this.enterPhone = enterPhone
    }
    constructor(enterName : String, enterPhone : Int){
        this.enterName = enterName
        this.enterPhone = enterPhone
    }
}
package com.pride.roomtest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TestName")
data class Name(

    @PrimaryKey(autoGenerate = true)
    val id : Int?,

    @ColumnInfo(name = "some_text")
    val text : String
)



package com.pride.roomtest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TestName")
data class Name(

    @PrimaryKey(autoGenerate = true)
    val id : Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "activator")
    val activator : String?,
    @ColumnInfo(name = "condition")
    val condition : String?,
    @ColumnInfo(name = "reward")
    val reward: String?,
    @ColumnInfo(name = "time")
    val time : String?,
    @ColumnInfo(name = "description")
    val description: String?
)



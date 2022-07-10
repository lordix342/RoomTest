package com.pride.roomtest

import android.database.Cursor
import androidx.room.*
import io.reactivex.rxjava3.core.Flowable

@Dao
interface NameDAO {
    @Query("SELECT * FROM TestName")
    fun getAll() : Flowable<Array<Name>>

    @Query("SELECT * FROM TestName WHERE some_text LIKE :text")
    fun findText(text : String) : Cursor

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToDB(name: Name)

    @Query("DELETE FROM TestName")
    fun clearDb()
}
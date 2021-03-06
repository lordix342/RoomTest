package com.pride.roomtest.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface NameDAO {
    @Query("SELECT * FROM TestName")
    fun getAll() : Flowable<Array<Name>>

    /*@Query("SELECT * FROM TestName WHERE some_text LIKE :text")
    fun findText(text : String) : Cursor*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToDB(name: Name):Completable

    @Query("DELETE FROM TestName")
    fun clearDb()
}
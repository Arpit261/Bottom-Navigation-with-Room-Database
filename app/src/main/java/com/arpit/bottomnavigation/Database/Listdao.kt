package com.arpit.bottomnavigation.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Listdao {

    @Insert
    fun insert(listEntity: ListEntity)

    @Delete
    fun delete(listEntity: ListEntity)

    @Query("SELECT * FROM lists")
    fun GetAllList():List<ListEntity>

    @Query("SELECT * FROM lists WHERE id = :listId")
    fun GetListById(listId:String):ListEntity


}
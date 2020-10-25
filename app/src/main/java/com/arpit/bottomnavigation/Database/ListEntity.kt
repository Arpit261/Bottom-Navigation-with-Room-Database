package com.arpit.bottomnavigation.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Lists")
data class ListEntity (

    @PrimaryKey val id:Int,
    @ColumnInfo (name = "list_name")val Listname:String,
    @ColumnInfo(name="list_price") val ListPlace:String
)

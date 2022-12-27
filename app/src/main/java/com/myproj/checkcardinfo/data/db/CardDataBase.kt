package com.myproj.checkcardinfo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myproj.checkcardinfo.data.db.dao.CardDao
import com.myproj.checkcardinfo.data.db.models.CardEntity

@Database(
    entities = [
        CardEntity::class,
    ], version = 1
)
abstract class CardDataBase: RoomDatabase() {
    abstract fun cardDao(): CardDao
}
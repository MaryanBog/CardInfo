package com.myproj.checkcardinfo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myproj.checkcardinfo.data.db.models.CardContract
import com.myproj.checkcardinfo.data.db.models.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM ${CardContract.CARD_TABLE_NAME}")
    fun getAllCardData(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cardData: CardEntity)
}
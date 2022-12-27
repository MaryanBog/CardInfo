package com.myproj.checkcardinfo.data

import com.myproj.checkcardinfo.data.db.models.CardEntity
import com.myproj.checkcardinfo.data.models.CardInfo
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun loadCardInfo(
        cardNumber: Int
    ): CardInfo

    suspend fun saveCard(cardData: CardEntity)

    fun getAllCard(): List<CardEntity>
}
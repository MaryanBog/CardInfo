package com.myproj.checkcardinfo.data

import com.myproj.checkcardinfo.data.db.dao.CardDao
import com.myproj.checkcardinfo.data.db.models.CardEntity
import com.myproj.checkcardinfo.data.models.CardInfo
import com.myproj.checkcardinfo.data.rest.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
) : CardRepository {
    override suspend fun loadCardInfo(
        cardNumber: Int
    ): CardInfo {
        return Network.cardApi.loadCardInfo(cardNumber)
    }

    override suspend fun saveCard(cardData: CardEntity) {
        cardDao.insert(cardData)
    }

    override fun getAllCard(): List<CardEntity> {
        return cardDao.getAllCardData()
    }
}
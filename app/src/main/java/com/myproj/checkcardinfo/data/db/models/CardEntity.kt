package com.myproj.checkcardinfo.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproj.checkcardinfo.data.models.CardInfo

@Entity(tableName = CardContract.CARD_TABLE_NAME)
data class CardEntity(
    @PrimaryKey
    @ColumnInfo(name = CardContract.Columns.CARD_ID)
    val id: Long?,
    @Embedded(prefix = "number_")
    val number: NumberEntity,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded(prefix = "country_")
    val country: CountryEntity,
    @Embedded(prefix = "bank_")
    val bank: BankEntity
){
    companion object{
        fun fromDomain(data: CardInfo) = CardEntity(
            id = null,
            number = NumberEntity.fromDomain(data.number),
            scheme = data.scheme,
            type = data.type,
            brand = data.brand,
            prepaid = data.prepaid,
            country = CountryEntity.fromDomain(data.country),
            bank = BankEntity.fromDomain(data.bank)
        )
    }

    fun toDomain() = CardInfo(
        number = number.toDomain(),
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = country.toDomain(),
        bank = bank.toDomain()
    )
}

package com.myproj.checkcardinfo.data.db.models

import androidx.room.ColumnInfo
import com.myproj.checkcardinfo.data.models.CountryInfo

data class CountryEntity(
    @ColumnInfo(name = CountryContract.Columns.COUNTRY_NUMERIC)
    val numeric: Int?,
    @ColumnInfo(name = CountryContract.Columns.COUNTRY_ALPHA)
    val alpha: String?,
    @ColumnInfo(name = CountryContract.Columns.COUNTRY_NAME)
    val name: String?,
    @ColumnInfo(name = CountryContract.Columns.COUNTRY_EMOJI)
    val emoji: String?,
    @ColumnInfo(name = CountryContract.Columns.COUNTRY_CURRENCY)
    val currency: String?,
    @ColumnInfo(name = CountryContract.Columns.COUNTRY_LATITUDE)
    val latitude: Int?,
    @ColumnInfo(name = CountryContract.Columns.COUNTRY_LONGITUDE)
    val longitude: Int?
){
    companion object{
        fun fromDomain(data: CountryInfo) = CountryEntity(
            numeric = data.numeric,
            alpha = data.alpha,
            name = data.name,
            emoji = data.emoji,
            currency = data.currency,
            latitude = data.latitude,
            longitude = data.longitude
        )
    }

    fun toDomain() = CountryInfo(
        numeric = numeric,
        alpha = alpha,
        name = name,
        emoji = emoji,
        currency = currency,
        latitude = latitude,
        longitude = longitude
    )
}

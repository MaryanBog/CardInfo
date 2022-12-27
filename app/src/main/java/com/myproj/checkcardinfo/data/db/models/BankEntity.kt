package com.myproj.checkcardinfo.data.db.models

import androidx.room.ColumnInfo
import com.myproj.checkcardinfo.data.models.BankInfo

data class BankEntity(
    @ColumnInfo(name = BankContract.Columns.BANK_NAME)
    val name: String?,
    @ColumnInfo(name = BankContract.Columns.BANK_URL)
    val urlString: String?,
    @ColumnInfo(name = BankContract.Columns.BANK_PHONE)
    val phone: String?,
    @ColumnInfo(name = BankContract.Columns.BANK_CITY)
    val city: String?
){
    companion object{
        fun fromDomain(data: BankInfo) = BankEntity(
            name = data.name,
            urlString = data.urlString,
            phone = data.phone,
            city = data.city
           )
    }

    fun toDomain() = BankInfo(
        name = name,
        urlString = urlString,
        phone = phone,
        city = city
    )
}
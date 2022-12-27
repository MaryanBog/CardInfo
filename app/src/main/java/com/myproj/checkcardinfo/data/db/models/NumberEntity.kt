package com.myproj.checkcardinfo.data.db.models

import androidx.room.ColumnInfo
import com.myproj.checkcardinfo.data.models.NumberInfo

data class NumberEntity(
    @ColumnInfo(name = NumberContract.Columns.NUMBER_LENGTH)
    val length: Int?,
    @ColumnInfo(name = NumberContract.Columns.NUMBER_AVAILABILITY)
    val availability: Boolean?
){
    companion object{
        fun fromDomain(data: NumberInfo) = NumberEntity(
            length = data.length,
            availability = data.availability
        )
    }

    fun toDomain() = NumberInfo(
        length = length,
        availability = availability
    )
}

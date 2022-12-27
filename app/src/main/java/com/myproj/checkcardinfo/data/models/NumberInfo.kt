package com.myproj.checkcardinfo.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class NumberInfo(
    val length: Int?,
    @Json(name = "luhn")
    val availability: Boolean?
) : Parcelable
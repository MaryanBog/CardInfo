package com.myproj.checkcardinfo.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class BankInfo(
    val name: String?,
    @Json(name = "url")
    val urlString: String?,
    val phone: String?,
    val city: String?
) : Parcelable

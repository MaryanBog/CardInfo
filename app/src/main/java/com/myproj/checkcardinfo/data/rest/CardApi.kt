package com.myproj.checkcardinfo.data.rest

import com.myproj.checkcardinfo.data.models.CardInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CardApi {
    @GET("{cardNumber}")
   suspend fun loadCardInfo(@Path("cardNumber") cardNumber: Int): CardInfo
}
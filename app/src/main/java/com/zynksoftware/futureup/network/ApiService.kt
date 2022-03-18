package com.zynksoftware.futureup.network

import com.zynksoftware.futureup.models.CryptoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v3/coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 250,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = true,
        @Query("price_change_percentage") priceChangePercentage: String = "24h"
    ): Response<List<CryptoModel>>
}
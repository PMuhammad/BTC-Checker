package com.example.btcchecker.retrofit

import com.example.btcchecker.dataclasses.CryptoDataClass
import retrofit2.Response
import retrofit2.http.GET

interface CryptoApi {

    @GET("assets/BTC")
    suspend fun getData(): Response<CryptoDataClass>

}
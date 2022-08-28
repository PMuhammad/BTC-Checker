package com.example.btcchecker.repo

import com.example.btcchecker.retrofit.CryptoApi
import javax.inject.Inject

class CryptoRepo @Inject constructor(private val cryptoApi: CryptoApi) {

    var btcPrice = 0
    var btcChange = 0.0

    suspend fun getBtcPrice(): Double {
        return cryptoApi.getData().body()?.asset?.price ?: 0.0
    }

    suspend fun getBtcChange(): Double{
        return cryptoApi.getData().body()?.asset?.change_24h ?: 0.0
    }


}
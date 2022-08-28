package com.example.btcchecker.dataclasses

data class Asset(
    val asset_id: String,
    val change_1h: Double,
    val change_24h: Double,
    val change_7d: Double,
    val circulating_supply: Int,
    val created_at: String,
    val description: String,
    val ethereum_contract_address: String,
    val fully_diluted_market_cap: Double,
    val market_cap: Double,
    val max_supply: Int,
    val name: String,
    val price: Double,
    val quote: Quote,
    val status: String,
    val total_supply: Int,
    val updated_at: String,
    val volume_24h: Double,
    val website: String
)
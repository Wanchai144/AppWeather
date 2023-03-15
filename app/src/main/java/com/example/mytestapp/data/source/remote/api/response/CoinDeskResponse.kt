package com.example.mytestapp.data.source.remote.api.response

import com.google.gson.annotations.SerializedName


data class CoinDeskResponse(
    @SerializedName("bpi")
    val bpi: Bpi,
    @SerializedName("chartName")
    val chartName: String,
    @SerializedName("disclaimer")
    val disclaimer: String,
    @SerializedName("time")
    val time: Time
)

data class Bpi(
    @SerializedName("EUR")
    val EUR: EUR,
    @SerializedName("GBP")
    val GBP: GBP,
    @SerializedName("USD")
    val USD: USD
)

data class Time(
    @SerializedName("updated")
    val updated: String,
    @SerializedName("updatedISO")
    val updatedISO: String,
    @SerializedName("updateduk")
    val updateduk: String
)

data class EUR(
    @SerializedName("code")
    val code: String? ="",
    @SerializedName("description")
    val description: String? ="",
    @SerializedName("rate")
    val rate: String? ="",
    @SerializedName("rate_float")
    val rate_float: Double? = 0.0,
    @SerializedName("symbol")
    val symbol: String? =""
)

data class GBP(
    @SerializedName("code")
    val code: String? ="",
    @SerializedName("description")
    val description: String? ="",
    @SerializedName("rate")
    val rate: String? ="",
    @SerializedName("rate_float")
    val rate_float: Double? = 0.0,
    @SerializedName("symbol")
    val symbol: String? =""
)

data class USD(
    @SerializedName("code")
    val code: String? ="",
    @SerializedName("description")
    val description: String? ="",
    @SerializedName("rate")
    val rate: String? ="",
    @SerializedName("rate_float")
    val rate_float: Double? = 0.0,
    @SerializedName("symbol")
    val symbol: String? =""
)
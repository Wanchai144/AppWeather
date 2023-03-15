package com.example.mytestapp.data.model


data class Coin (
    var time: String,
    var chartName:String,
    var bpi: ArrayList<DataBpi>? = arrayListOf()
)


data class DataBpi(
    var code: String? ="",
    var description: String? ="",
    var rate: String? ="",
    var rate_float: Double? = 0.0,
    var symbol: String? ="",
    var type: String? = ""
)






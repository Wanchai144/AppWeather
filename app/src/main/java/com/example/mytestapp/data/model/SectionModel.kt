package com.example.mytestapp.data.model

import retrofit2.http.Header


data class SectionModel(
    var chartName:String,
    var section: Section? = null
)

data class Section(val header: ArrayList<Headers>? = arrayListOf(), val items: ArrayList<DataItem>? = arrayListOf())

data class Headers(
    var time: String? = ""
)

data class DataItem(
    var code: String? ="",
    var description: String? ="",
    var rate: String? ="",
    var rate_float: Double? = 0.0,
    var symbol: String? ="",
    var type: String? = ""
)

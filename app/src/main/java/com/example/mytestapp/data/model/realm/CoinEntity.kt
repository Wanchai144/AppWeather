package com.example.mytestapp.data.model.room

import androidx.room.Entity
import com.example.mytestapp.utils.Const.ROOM_DATABASE_TABLE_COIN
import androidx.room.ColumnInfo
import androidx.room.Index
import androidx.room.PrimaryKey
import io.realm.RealmList
import io.realm.RealmObject

//@Entity(
//    tableName = ROOM_DATABASE_TABLE_COIN
//)
//data class CoinEntity(
//    @PrimaryKey(autoGenerate = true) var rowId: Int = 0,
//    @ColumnInfo(name = "time") var time: String = "",
//    @ColumnInfo(name = "chartName") var chartName: String? = "",
//    @ColumnInfo(name = "bpi") var bpi: ArrayList<BPIEntity>? = arrayListOf()
//)
//
//data class BPIEntity(
//    @ColumnInfo(name = "code") var code: String? = "",
//    @ColumnInfo(name = "description") var description: String? = "",
//    @ColumnInfo(name = "rate") var rate: String? = "",
//    @ColumnInfo(name = "rate_float") var rate_float: Double? = 0.0,
//    @ColumnInfo(name = "symbol") var symbol: String? = "",
//    @ColumnInfo(name = "type") var type: String? = ""
//)

//
//open class CoinEntity(
//    @PrimaryKey(autoGenerate = true) var rowId: Int = 0,
//    @ColumnInfo(name = "time") var time: String = "",
//    @ColumnInfo(name = "chartName") var chartName: String? = "",
//    @ColumnInfo(name = "bpi") var bpi: ArrayList<BPIEntity>? = arrayListOf()
//):

open class CoinEntity(
    @PrimaryKey
    var id: Long = 0,
    var time: String = "",
    var chartName: String? = "",
    var bpi: RealmList<BPIEntity> = RealmList()
) : RealmObject()

open class BPIEntity(
    var code: String? = "",
    var description: String? = "",
    var rate: String? = "",
    var rate_float: Double? = 0.0,
    var symbol: String? = "",
    var type: String? = ""
) : RealmObject()
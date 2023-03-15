package com.example.mytestapp.domain.usecase


import com.example.mytestapp.data.model.Coin
import com.example.mytestapp.data.model.DataBpi
import com.example.mytestapp.data.model.room.BPIEntity
import com.example.mytestapp.data.model.room.CoinEntity
import com.example.mytestapp.data.repository.CoinDeskRepository
import com.example.mytestapp.data.source.remote.api.response.CoinDeskResponse
import io.realm.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import kotlin.collections.ArrayList


interface GetDataUseCase {
    fun execute(): Flow<Coin>
    fun saveData(coin: Coin): Flow<Unit>
}

class GetDataUseCaseImpl(private val coinDeskRepository: CoinDeskRepository) : GetDataUseCase {
    override fun execute(): Flow<Coin> = flow {
        coinDeskRepository.coinDesk().collect { data ->
            val getDataCoin = mapDataCoinModel(data)
            emit(getDataCoin)
        }
    }

    override fun saveData(coin: Coin): Flow<Unit> = flow {
        val map = mapDataCoinEntity(coin)
    coinDeskRepository.saveDataCoinDeskLocal(map).collect { data ->
            emit(data)
        }
    }


    private fun mapDataCoinEntity(coin: Coin): CoinEntity {
        coin.let { res ->
            val answersList : RealmList<BPIEntity> = RealmList()
            res.bpi?.forEach {
                answersList.add(BPIEntity(code = it.code, description = it.description, rate = it.rate, rate_float = it.rate_float, symbol = it.symbol, type = it.type))
            }
//
            val data: CoinEntity = CoinEntity().apply {
                id = UUID.randomUUID().mostSignificantBits
                chartName = res.chartName
                 bpi = answersList
            }
            return data
        }
    }
}


private fun mapDataCoinModel(coin: CoinDeskResponse): Coin {
    coin.let {
        val bpi = ArrayList<DataBpi>()
        bpi.add(
            element = DataBpi(
                code = it.bpi.EUR.code,
                description = it.bpi.EUR.description,
                rate = it.bpi.EUR.rate,
                rate_float = it.bpi.EUR.rate_float,
                symbol = it.bpi.EUR.symbol,
                type = "EUR"
            )
        )
        bpi.add(
            element = DataBpi(
                code = it.bpi.GBP.code,
                description = it.bpi.GBP.description,
                rate = it.bpi.GBP.rate,
                rate_float = it.bpi.GBP.rate_float,
                symbol = it.bpi.GBP.symbol,
                type = "GBP"
            )
        )
        bpi.add(
            element = DataBpi(
                code = it.bpi.USD.code,
                description = it.bpi.USD.description,
                rate = it.bpi.USD.rate,
                rate_float = it.bpi.USD.rate_float,
                symbol = it.bpi.USD.symbol,
                type = "USD"
            )
        )
        return Coin(
            time = it.time.updatedISO, chartName = it.chartName,
            bpi = bpi
        )
    }
}





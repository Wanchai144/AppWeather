package com.example.mytestapp.domain.usecase


import android.util.Log
import com.example.mytestapp.data.model.*
import com.example.mytestapp.data.model.room.BPIEntity
import com.example.mytestapp.data.model.room.CoinEntity
import com.example.mytestapp.data.repository.CoinDeskRepository
import io.realm.RealmList
import kotlinx.coroutines.flow.*

interface LoadDataRetrospectUseCase {
    fun execute(): Flow<List<Coin>>
}

class LoadDataRetrospectUseCaseImpl(private val coinDeskRepository: CoinDeskRepository) :
    LoadDataRetrospectUseCase {
    override fun execute(): Flow<List<Coin>> = flow {
        coinDeskRepository.getDataCoinDeskFromLocal().collect() { dataEntiry ->
            val history = mapDataRetrospectModel(dataEntiry)
            emit(history)
        }
    }

    private fun mapDataRetrospectModel(coin: List<CoinEntity>): List<Coin> {
        coin.let { it ->
            val coins = ArrayList<Coin>()
            val item = ArrayList<DataBpi>()

            it.forEach { its ->
                val bpiList: RealmList<BPIEntity> = its.bpi
                bpiList.forEach { bpi ->
                    item.add(
                        element = DataBpi(
                            code = bpi.code,
                            description = bpi.description,
                            rate = bpi.rate,
                            rate_float = bpi.rate_float,
                            symbol = bpi.symbol,
                            type = bpi.type
                        )
                    )
                    coins.add(Coin(time = its.time, chartName = its.chartName.orEmpty(), bpi = item))
                }
            }
            return coins
        }
    }
}

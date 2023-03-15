package com.example.mytestapp.data.source.remote.api.service



import com.example.mytestapp.data.source.remote.api.response.CoinDeskResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("currentprice.json")
    suspend fun getCoinDesk(): Response<CoinDeskResponse>

}
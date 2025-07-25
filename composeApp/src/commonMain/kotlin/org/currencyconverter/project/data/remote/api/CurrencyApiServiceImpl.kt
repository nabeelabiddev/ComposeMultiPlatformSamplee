package org.currencyconverter.project.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.currencyconverter.project.domain.CurrencyApiService
import org.currencyconverter.project.domain.PrefrenceRepository
import org.currencyconverter.project.domain.RequestState
import org.currencyconverter.project.domain.model.ApiResponse
import org.currencyconverter.project.domain.model.Currency

class CurrencyApiServiceImpl (
    private val prefrenceRepository: PrefrenceRepository
): CurrencyApiService {

    override suspend fun getLatestExchangeRate(): RequestState<List<Currency>> {
      return try {
             val response = httpClient.get(ENDPOINT)
          if (response.status.value == 200){
              val apiResponse = Json.decodeFromString<ApiResponse>(response.body())
              val lastupdated = apiResponse.meta.lastUpdatedAt
              prefrenceRepository.saveLastUpdated(lastupdated)
              println("API Response${response.body<String>()}")
              RequestState.Success(data = apiResponse.data.values.toList())
          }else{
              RequestState.Error(message = "HTTP Error Code: ${response.status}")
          }
      }catch (e: Exception){
          RequestState.Error(message = e.message.toString())
      }
    }
    private val httpClient = HttpClient{
        install(ContentNegotiation){
            json(Json{
                 prettyPrint = true
                 isLenient = true
                ignoreUnknownKeys=true
            })
        }
        install(HttpTimeout){
            requestTimeoutMillis =15000
        }
        install(DefaultRequest){
            headers{
                append("apikey",API_KEY)
            }
        }
    }

    companion object{
        const val ENDPOINT ="https://api.currencyapi.com/v3/latest"
        const val API_KEY="cur_live_4SOOUstIRtxoQkPHiOuXZn4FbGCS9UOM9Mx2v8m9"
    }

}
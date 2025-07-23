package org.currencyconverter.project.presentation.screen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.currencyconverter.project.domain.CurrencyApiService
import org.currencyconverter.project.domain.PrefrenceRepository
import org.currencyconverter.project.domain.model.RateStatus


sealed class HomeUiEvent{
    data object  RefreshRate : HomeUiEvent()
    data object SwitchCurrencies: HomeUiEvent()
    data class SaveSourceCurrencyCode(val code: String): HomeUiEvent()
    data class SaveTargetCurrencyCode(val code: String): HomeUiEvent()
}

class HomeViewModel(
    val prefrenceRepository: PrefrenceRepository,
    val currencyApiServiceImpl: CurrencyApiService
) : ScreenModel {

    private var _rateStatus: MutableState<RateStatus> = mutableStateOf(RateStatus.IDEL)
    val rateStatus: State<RateStatus> = _rateStatus


    init {
        screenModelScope.launch{
            fetchNewRates()
            getRateStatus()
        }
    }

    fun sendEvent(homeUiEvent: HomeUiEvent){
        when(homeUiEvent){
            HomeUiEvent.RefreshRate -> {
                screenModelScope.launch{
                    fetchNewRates()
                }
            }
            HomeUiEvent.SwitchCurrencies -> {

            }

            is HomeUiEvent.SaveSourceCurrencyCode -> {

            }
            is HomeUiEvent.SaveTargetCurrencyCode -> {

            }
        }
    }


    private suspend fun fetchNewRates(){
        try {

           currencyApiServiceImpl.getLatestExchangeRate()
        }catch (e: Exception){
            println(e.message)
        }
    }


    private suspend fun getRateStatus(){
        _rateStatus.value = if (prefrenceRepository.isDataFresh(
            currentTimeStamp = Clock.System.now().toEpochMilliseconds()
        )) RateStatus.FRESH
        else RateStatus.STALE
    }

}
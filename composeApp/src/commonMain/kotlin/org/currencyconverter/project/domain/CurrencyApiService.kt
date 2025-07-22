package org.currencyconverter.project.domain

import org.currencyconverter.project.domain.model.Currency

interface CurrencyApiService {

  suspend  fun getLatestExchangeRate () : RequestState<List<Currency>>
}
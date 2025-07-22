package org.currencyconverter.project.presentation.screen

import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.currencyconverter.project.data.remote.api.CurrencyApiServiceImpl

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {
//            CurrencyApiServiceImpl().getLatestExchangeRate()
        }
    }


}
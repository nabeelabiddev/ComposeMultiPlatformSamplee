package org.currencyconverter.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.currencyconverter.project.presentation.component.HomeHeader
import org.currencyconverter.project.ui.theme.surfaceDark
import androidx.compose.runtime.getValue

class HomeScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel =getScreenModel<HomeViewModel>()
        val rateStatus by viewModel.rateStatus

        Column(
            modifier = Modifier.fillMaxSize().background(surfaceDark)
        ) {
            HomeHeader(
                status = rateStatus,
                onRatesRefresh = {}
            )
        }
    }


}
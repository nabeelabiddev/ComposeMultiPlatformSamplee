package org.currencyconverter.project

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.currencyconverter.project.di.initializekoin
import org.currencyconverter.project.presentation.screen.HomeScreen
import org.currencyconverter.project.ui.theme.darkScheme
import org.currencyconverter.project.ui.theme.lightScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    initializekoin()
    val color = if (isSystemInDarkTheme()) darkScheme else lightScheme
    MaterialTheme (colorScheme = color) {
        Navigator(HomeScreen())
    }
}
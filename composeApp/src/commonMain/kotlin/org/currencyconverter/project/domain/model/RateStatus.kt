package org.currencyconverter.project.domain.model

import androidx.compose.ui.graphics.Color

enum class RateStatus(
    val title:String,
    val color : Color
) {
    IDEL(
        title = "Rates",
        color = Color.White
    ),
    FRESH(
        title = "Fresh Rates",
        color = Color.Red
    ),
    STALE(
        title = "Rates",
        color = Color.Green
    )
}
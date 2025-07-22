package org.currencyconverter.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
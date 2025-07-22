package org.currencyconverter.project.di

import com.russhwolf.settings.Settings
import org.currencyconverter.project.data.local.PrefrenceRepositoryImpl
import org.currencyconverter.project.data.remote.api.CurrencyApiServiceImpl
import org.currencyconverter.project.domain.CurrencyApiService
import org.currencyconverter.project.domain.PrefrenceRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appmodule = module {
    single { Settings() }
    single<PrefrenceRepository> { PrefrenceRepositoryImpl(settings = get()) }
    single<CurrencyApiService> { CurrencyApiServiceImpl(prefrenceRepository = get()) }
}

fun initializekoin (){
    startKoin {
        modules (appmodule)
    }
}
package org.currencyconverter.project.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.currencyconverter.project.domain.PrefrenceRepository

@Suppress("EQUALITY_NOT_APPLICABLE_WARNING")
class PrefrenceRepositoryImpl(private val settings: Settings) : PrefrenceRepository {

    companion object{
        const val TIME_STAMP_KEY="lastUpdated"
    }

    @OptIn(ExperimentalSettingsApi::class)
    private val flowSettings : FlowSettings =(settings as ObservableSettings).toFlowSettings()

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun saveLastUpdated(lastUpdated: String) {
        flowSettings.putLong(
            key = TIME_STAMP_KEY,
            value = Instant.parse(lastUpdated).toEpochMilliseconds()
        )
    }

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun isDataFresh(currentTimeStamp: Long): Boolean {
        val savedtimestamp =flowSettings.getLong(
            key = TIME_STAMP_KEY,
            defaultValue = 0L
        )

        return if (savedtimestamp != 0L){
              val currentInstant = Instant.fromEpochMilliseconds(currentTimeStamp)
              val savedtime = Instant.fromEpochMilliseconds(savedtimestamp)

             val currenttime=currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())


            val savedtimen=savedtime.toLocalDateTime(TimeZone.currentSystemDefault())
             val daydifference = currenttime.date.dayOfYear - savedtimen.date.dayOfYear
               daydifference < 1
        }else false
    }


}
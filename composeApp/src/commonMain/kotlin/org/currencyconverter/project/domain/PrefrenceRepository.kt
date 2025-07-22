package org.currencyconverter.project.domain

interface PrefrenceRepository {
   suspend fun saveLastUpdated(lastUpdated : String)
    suspend fun isDataFresh(currentTimeStamp : Long): Boolean
}
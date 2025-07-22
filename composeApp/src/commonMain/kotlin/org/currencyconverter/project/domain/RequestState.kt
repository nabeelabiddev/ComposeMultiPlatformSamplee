package org.currencyconverter.project.domain

sealed class RequestState<out T> {
    data object Idel : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val message : String) : RequestState<Nothing>()


    fun isloading() : Boolean = this is Loading
    fun isError() : Boolean = this is Error
    fun isSuccess() : Boolean = this is Success

    fun getSuccessData()=(this as Success).data
    fun getErrorMessage()=(this as Error).message
}
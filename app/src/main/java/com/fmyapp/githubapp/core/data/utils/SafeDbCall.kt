package com.fmyapp.githubapp.core.data.utils

import java.io.IOException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class SafeDbCall {
    open fun <T> safeDbCall(
        dbCall: suspend () -> T
    ): Flow<Result<T>> {
        return flow {
            emit(Result.Loading)
            try {
                delay(1000)
                val result = dbCall.invoke()
                if (result != null) emit(Result.Success(result))
                else emit(Result.Success(result))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(errorMessage = e.message))
            }
        }
    }
}
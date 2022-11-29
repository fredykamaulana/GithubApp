package com.fmyapp.githubapp.core.data.utils

import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

open class SafeApiCall {

    open suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Flow<Result<T>> {
        return flow {
            try {
                val result = apiCall.invoke()
                if (result != null) emit(Result.Success(result)) else emit(Result.Empty)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        when (throwable.code()) {
                            in 400..451 -> emit(parseHttpError(HttpResult.CLIENT_ERROR, throwable))
                            in 500..599 -> emit(parseHttpError(HttpResult.SERVER_ERROR, throwable))
                            else -> emit(
                                error(
                                    HttpResult.NOT_DEFINED,
                                    throwable.code(),
                                    "Undefined error",
                                    null
                                )
                            )
                        }
                    }
                    is UnknownHostException -> emit(
                        error(
                            HttpResult.NO_CONNECTION,
                            null,
                            "No internet connection",
                            null
                        )
                    )
                    is SocketTimeoutException -> emit(
                        error(
                            HttpResult.TIMEOUT,
                            null,
                            "Slow connection",
                            null
                        )
                    )
                    is IOException -> emit(
                        error(
                            HttpResult.BAD_RESPONSE,
                            null,
                            throwable.message,
                            null
                        )
                    )
                    else -> error(HttpResult.NOT_DEFINED, null, throwable.message, null)
                }
            }
        }
    }

    private fun parseHttpError(cause: HttpResult, throwable: HttpException): Result<Nothing> {
        return try {
            val errorResponse =
                throwable.response()?.errorBody()?.string() ?: "Unknown HTTP error body"
            val errorMessage = errorResponse.getOrNull(JSON_KEY_MESSAGE)
            val errorStatus = errorResponse.getOrNull(JSON_KEY_STATUS)
            error(cause, throwable.code(), errorMessage, errorStatus)
        } catch (exception: Exception) {
            error(cause, throwable.code(), exception.localizedMessage, null)
        }
    }

    private fun error(
        cause: HttpResult,
        code: Int?,
        errorMessage: String?,
        errorStatus: String?
    ): Result.Error {
        return Result.Error(cause, code, errorMessage, errorStatus)
    }

    @Suppress("SwallowedException")
    private fun String.getOrNull(key: String): String? {
        return try {
            val json = JSONObject(this)
            json.getString(key)
        } catch (e: JSONException) {
            null
        }
    }

    companion object {
        private const val JSON_KEY_MESSAGE = "message"
        private const val JSON_KEY_STATUS = "status"
    }
}

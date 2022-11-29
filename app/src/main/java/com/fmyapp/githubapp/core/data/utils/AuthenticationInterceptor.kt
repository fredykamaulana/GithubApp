package com.fmyapp.githubapp.core.data.utils

import com.fmyapp.githubapp.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().apply {
            addBearerAuthorization()
        }

        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }

    private fun Request.Builder.addBearerAuthorization(): Request.Builder {
        val token = BuildConfig.TOKEN
        return this.addHeader("Authorization", "token $token")
    }
}

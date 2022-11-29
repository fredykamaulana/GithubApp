package com.fmyapp.githubapp.core.utils

import android.content.Context
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

fun String.getJsonDataFromAsset(context: Context, action: () -> Unit = {}): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(this).bufferedReader().use { it.readText() }
    } catch (exception: IOException) {
        exception.printStackTrace()
        action()
        return null
    }

    Log.e("JsonString", jsonString)
    return jsonString
}

inline fun <reified T> String.jsonStringToPojo(): T {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val listType = Types.newParameterizedType(T::class.java)
    val adapter: JsonAdapter<T> = moshi.adapter(listType)
    val result = adapter.fromJson(this)

    Log.e("DataObject", result.toString())
    return result as T
}
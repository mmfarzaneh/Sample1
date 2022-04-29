package com.sample.data.repository

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsRepository @Inject constructor(context: Context) {
    private val prefs = context.getSharedPreferences("WORD", Context.MODE_PRIVATE)



    internal fun getBooleanFromCache(key: String, def: Boolean): Boolean {
        return prefs.getBoolean(key, def)
    }

    fun putBooleanToCache(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }
}
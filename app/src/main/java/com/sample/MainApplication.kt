package com.sample

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.data.entity.WordEntity
import com.sample.data.repository.SharedPrefsRepository
import com.sample.data.repository.WordRepository
import dagger.hilt.android.HiltAndroidApp
import java.io.IOException
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var repository: SharedPrefsRepository

    @Inject
    lateinit var wordRepository: WordRepository

    override fun onCreate() {
        super.onCreate()
        if (!repository.getBooleanFromCache("is_added_to_db", false)) {
            val loadJSONFromAsset = getWorks()
            wordRepository.addWord(loadJSONFromAsset as ArrayList)
            repository.putBooleanToCache("is_added_to_db", true)
        }
        var a = 0
    }

    private var jsonFileString: String? = ""

    private fun getWorks(): List<WordEntity> {
        jsonFileString = getJsonDataFromAsset(this, "words.json")

        val gson = Gson()
        val listPersonType = object : TypeToken<List<WordEntity>>() {}.type

        return gson.fromJson(jsonFileString, listPersonType)
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}
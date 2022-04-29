package com.sample.data.repository

import androidx.lifecycle.LiveData
import com.sample.data.entity.WordEntity
import com.sample.data.local.WordDao
import javax.inject.Inject

class WordRepository @Inject constructor(private var wordDao: WordDao) {

    fun addWord(word: WordEntity) {
        wordDao.addWord(word)
    }

    fun addWord(words: ArrayList<WordEntity>) {
        wordDao.addWord(words)
    }

    fun allWordItems(): LiveData<List<WordEntity>> {
            return wordDao.allWordItems
    }

    fun search(query: String): LiveData<List<WordEntity>> {
        return wordDao.searchWord(query)
    }

    fun getItemById(id: Int): WordEntity {
        return wordDao.getItemById(id)
    }

}
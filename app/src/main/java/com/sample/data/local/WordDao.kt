package com.sample.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sample.data.entity.WordEntity


@Dao
interface WordDao {
    @get:Query("select * from word")
    val allWordItems: LiveData<List<WordEntity>>

    @Query("select * from word where id = :id")
    fun getItemById(id: Int): WordEntity

    @Insert(onConflict = IGNORE)
    fun addWord(WordEntity: WordEntity)

    @Insert(onConflict = IGNORE)
    fun addWord(words: ArrayList<WordEntity>)

    @Query("select * from word WHERE title LIKE '%' || :query || '%' or description LIKE '%' || :query || '%'")
    fun searchWord(query: String): LiveData<List<WordEntity>>

    @Delete
    fun deleteWord(WordEntity: WordEntity)
}
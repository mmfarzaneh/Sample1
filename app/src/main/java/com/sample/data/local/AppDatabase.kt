package com.sample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.data.entity.WordEntity


@Database(
    entities = [
        WordEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val wordDao: WordDao
}

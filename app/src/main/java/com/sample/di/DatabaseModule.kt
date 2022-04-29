package com.sample.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sample.data.local.AppDatabase
import com.sample.data.local.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context?): AppDatabase {
        return Room.databaseBuilder(context!!, AppDatabase::class.java, "db_word")
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideMessageItemDao(database: AppDatabase): WordDao {
        return database.wordDao
    }
}
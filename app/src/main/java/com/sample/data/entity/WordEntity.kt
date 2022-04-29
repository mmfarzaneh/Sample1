package com.sample.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word")
class WordEntity(
    val title: String, val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
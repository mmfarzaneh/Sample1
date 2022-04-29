package com.sample.ui.words

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.data.entity.WordEntity
import com.sample.data.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {

    fun getWords(query: String): LiveData<List<WordEntity>> {
        if (query=="")
            return repository.allWordItems()
        return repository.search(query)
    }

}
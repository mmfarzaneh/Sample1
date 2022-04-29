package com.sample.ui.wordDetail

import androidx.lifecycle.ViewModel
import com.sample.data.entity.WordEntity
import com.sample.data.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {

    fun getWord(id: Int): WordEntity {
       return repository.getItemById(id)
    }

}
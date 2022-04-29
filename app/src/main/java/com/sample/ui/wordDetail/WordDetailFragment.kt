package com.sample.ui.wordDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sample.R
import com.sample.data.entity.WordEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_word_detail.*


@AndroidEntryPoint
class WordDetailFragment : Fragment() {

    private val viewModel: WordDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_word_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { bindCharacter(viewModel.getWord(it)) }
        back_iv.setOnClickListener { activity?.onBackPressed() }
    }

    private fun bindCharacter(word: WordEntity) {
        name_tv.text = word.title
        desc_tv.text = word.description
    }
}
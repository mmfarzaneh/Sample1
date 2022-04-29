package com.sample.ui.words

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.R
import com.sample.data.entity.WordEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_words.*
import kotlinx.android.synthetic.main.fragment_words.view.*


@AndroidEntryPoint
class WordsFragment : Fragment(), WordsAdapter.WordItemListener {

    private val viewModel: WordsViewModel by viewModels()
    private lateinit var adapter: WordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        setupObservers()
        et_search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                editable: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (editable.toString().isNotEmpty()) {
                    val currentText: String = editable.toString()
                    Handler().postDelayed({
                        if (currentText == et_search.text.toString()) {
                            adapter.clear()
                            viewModel.getWords(editable.toString()).observe(
                                viewLifecycleOwner
                            ) {
                                adapter.clear()
                                adapter.addItems(it as ArrayList<WordEntity>)
                            }
                        }
                    }, 700)
                } else {
                    viewModel.getWords("").observe(
                        viewLifecycleOwner
                    ) {
                        adapter.clear()
                        adapter.addItems(it as ArrayList<WordEntity>)
                    }
                }
            }
        })
    }

    private fun setupRecyclerView(view: View) {
        adapter = WordsAdapter(this)
        view.words_rv.layoutManager = LinearLayoutManager(requireContext())
        view.words_rv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getWords("").observe(
            viewLifecycleOwner
        ) { adapter.addItems(it as ArrayList<WordEntity>) }
    }

    override fun onClickedWord(wordId: Int) {
        findNavController().navigate(
            R.id.action_wordsFragment_to_wordDetailFragment,
            bundleOf("id" to wordId)
        )
    }

}
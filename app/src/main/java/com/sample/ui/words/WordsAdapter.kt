package com.sample.ui.words

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.data.entity.WordEntity
import com.sample.databinding.ItemWordBinding

class WordsAdapter(private val listener: WordItemListener) : RecyclerView.Adapter<WordViewHolder>() {

    interface WordItemListener {
        fun onClickedWord(wordId: Int)
    }

    private var items = ArrayList<WordEntity>()

    fun setItems(items: ArrayList<WordEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding: ItemWordBinding = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind(items[position])


    fun addItems(borrowModelList: ArrayList<WordEntity>) {
        this.items = borrowModelList
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}

class WordViewHolder(private val itemBinding: ItemWordBinding, private val listener: WordsAdapter.WordItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var word: WordEntity

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: WordEntity) {
        this.word = item
        itemBinding.name.text = item.title
        itemBinding.desc.text = item.description
    }

    override fun onClick(v: View?) {
        listener.onClickedWord(word.id)
    }
}

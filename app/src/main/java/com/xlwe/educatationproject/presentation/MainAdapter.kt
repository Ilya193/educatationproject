package com.xlwe.educatationproject.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xlwe.educatationproject.core.Book
import com.xlwe.educatationproject.databinding.BookLayoutBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val books = ArrayList<Book>()

    fun update(new: List<Book>) {
        books.clear()
        books.addAll(new)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: BookLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(book: Book) {
                binding.textView.text = book.name
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            BookLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }
}
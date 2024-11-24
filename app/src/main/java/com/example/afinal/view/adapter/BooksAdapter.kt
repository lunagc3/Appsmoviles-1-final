package com.example.afinal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.R
import com.example.afinal.model.Books

class BooksAdapter(private var booksList : MutableList<Books>) : RecyclerView.Adapter<BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BooksViewHolder(layoutInflater.inflate(R.layout.item_books, parent, false))
    }

    override fun getItemCount(): Int = booksList.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val item = booksList[position]
        holder.render(item)
    }
    fun updateBooks(newBooks: MutableList<Books>){
        this.booksList = newBooks
    }
    fun editBook(books: Books){
        val index = this.booksList.indexOfFirst { it.title == books.title }

    }
}
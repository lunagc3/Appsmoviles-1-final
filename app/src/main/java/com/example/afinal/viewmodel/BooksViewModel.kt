package com.example.afinal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.afinal.model.BookList
import com.example.afinal.model.Books
import com.example.afinal.model.BooksProvider

class BooksViewModel : ViewModel() {
    val _books = MutableLiveData<MutableList<Books>>(mutableListOf())
    val booksList : LiveData<MutableList<Books>> get()= _books
    init {
        _books.value = BookList.listOfBooks
    }
     fun addBook(book: Books){
        BooksProvider.addBook(book)
        _books.value = BooksProvider.booksList
    }
    fun editBook(book: Books){
        BooksProvider.editBook(book)
        _books.value = BooksProvider.booksList
    }
    fun deleteBook(book : Books){
        BooksProvider.deleteBook(book)
        _books.value = BooksProvider.booksList
    }
}
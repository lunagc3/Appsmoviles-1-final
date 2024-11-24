package com.example.afinal.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.databinding.ActivityMainBinding
import com.example.afinal.model.BookList
import com.example.afinal.model.Books
import com.example.afinal.model.BooksProvider
import com.example.afinal.model.BooksProvider.Companion.booksList
import com.example.afinal.view.adapter.BooksAdapter
import com.example.afinal.viewmodel.BooksViewModel

class MainActivity : ComponentActivity() {
    private lateinit var binding : ActivityMainBinding
    private val booksViewModel : BooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booksViewModel._books.observe(this, Observer {
            initRecycler(booksList)
        })
        adapter = BooksAdapter(booksList)
        binding.btnSecondActivity.setOnClickListener{
            Toast.makeText(this, "Agregarás un libro a la colección", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initRecycler(listOfBooks: MutableList<Books>) {
        binding.recyclerBooks.adapter = BooksAdapter(listOfBooks)
        binding.recyclerBooks.layoutManager = LinearLayoutManager(this)
    }
    fun deleteBook(book : Books){
        booksViewModel.deleteBook(book)
    }

}
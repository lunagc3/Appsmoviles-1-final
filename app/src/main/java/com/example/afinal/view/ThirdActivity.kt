package com.example.afinal.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.databinding.ActivityThirdBinding
import com.example.afinal.model.BookList
import com.example.afinal.model.Books
import com.example.afinal.model.BooksProvider
import com.example.afinal.view.adapter.BooksAdapter
import com.example.afinal.viewmodel.BooksViewModel

class ThirdActivity : ComponentActivity() {
    private lateinit var binding: ActivityThirdBinding
    private val booksViewModel : BooksViewModel by viewModels()
    private lateinit var booksAdapter: BooksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booksAdapter = BooksAdapter(BooksProvider.booksList)
        booksViewModel._books.observe(this, Observer {
            booksAdapter.notifyDataSetChanged()
            initRecycler(BooksProvider.booksList)
        })
        binding.btnSecondActivity.setOnClickListener{
            Toast.makeText(this, "Agregarás un libro a la colección", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
    private fun initRecycler(listOfBooks: MutableList<Books>){
        binding.recyclerBooks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerBooks.adapter = BooksAdapter(listOfBooks)
    }
    fun deleteBook(book : Books){
        booksViewModel.deleteBook(book)
    }
}
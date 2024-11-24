package com.example.afinal.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.afinal.databinding.ActivitySecondBinding
import com.example.afinal.model.Books
import com.example.afinal.model.BooksProvider
import com.example.afinal.view.adapter.BooksAdapter
import com.example.afinal.viewmodel.BooksViewModel

class SecondActivity : ComponentActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val booksViewModel : BooksViewModel by viewModels()
    private lateinit var booksAdapter: BooksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booksAdapter = BooksAdapter(BooksProvider.booksList)
        booksViewModel.booksList.observe(this){ books ->
            booksAdapter.updateBooks(books)
            booksAdapter.notifyDataSetChanged()
        }
        binding.btnAdd.setOnClickListener{
            val alertConfirmation : AlertDialog.Builder = AlertDialog.Builder(this)
            alertConfirmation.setMessage("¿Seguro/a que los datos son correctos?")
            alertConfirmation.setCancelable(false)
            alertConfirmation.setPositiveButton("Sí"){_,_->
                addBook()
            }
            alertConfirmation.setNegativeButton("No"){_,_->
               Toast.makeText(this,"Click en no", Toast.LENGTH_SHORT).show()
            }
            alertConfirmation.create()
           alertConfirmation.show()
        }
    }
         fun addBook(){
        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val genre = binding.etGenre.text.toString()
        val synopsis = binding.etSynopsis.text.toString()
        if (title.isNotBlank() && author.isNotBlank() && genre.isNotBlank() && synopsis.isNotBlank()) {
            val book = Books(title, author, genre, synopsis)
            booksViewModel.addBook(book)
            booksViewModel.booksList.observe(this, Observer {
                booksAdapter.notifyDataSetChanged()
            })
            clearInputs()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else{
            val alertConfirmation : AlertDialog.Builder = AlertDialog.Builder(this)
            alertConfirmation.setMessage("Hubo un error al cargar los datos")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
     fun clearInputs(){
        binding.etGenre.text.clear()
        binding.etTitle.text.clear()
        binding.etAuthor.text.clear()
        binding.etSynopsis.text.clear()
    }
}
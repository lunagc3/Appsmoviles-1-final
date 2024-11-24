package com.example.afinal.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.afinal.databinding.ActivityFourthBinding
import com.example.afinal.databinding.ActivityMainBinding
import com.example.afinal.model.Books
import com.example.afinal.model.BooksProvider
import com.example.afinal.view.adapter.BooksAdapter
import com.example.afinal.view.adapter.BooksViewHolder
import com.example.afinal.viewmodel.BooksViewModel

class FourthActivity : ComponentActivity() {
    private lateinit var binding: ActivityFourthBinding
    private val booksViewModel: BooksViewModel by viewModels()
    private lateinit var booksAdapter: BooksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booksAdapter = BooksAdapter(BooksProvider.booksList)
        booksViewModel.booksList.observe(this){ books ->
            booksAdapter.updateBooks(books)
            booksAdapter.notifyDataSetChanged()
        }


        val bookTitle = intent.getStringExtra("book_title") ?: ""
        val bookAuthor = intent.getStringExtra("book_author") ?: ""
        val bookGenre = intent.getStringExtra("book_genre") ?: ""
        val bookSynopsis = intent.getStringExtra("book_synopsis") ?: ""
        binding.etTitle.setText(bookTitle)
        binding.etAuthor.setText(bookAuthor)
        binding.etGenre.setText(bookGenre)
        binding.etSynopsis.setText(bookSynopsis)

        binding.btnEdit.setOnClickListener{
            val alertConfirmation : AlertDialog.Builder = AlertDialog.Builder(this)
            alertConfirmation.setMessage("¿Seguro/a que los datos son correctos?")
            alertConfirmation.setCancelable(false)
            alertConfirmation.setPositiveButton("Sí"){_,_->
                editBook()
            }
            alertConfirmation.setNegativeButton("No"){_,_->
                Toast.makeText(this,"Click en no", Toast.LENGTH_SHORT).show()
            }
            alertConfirmation.create()
            alertConfirmation.show()
        }

    }
    fun editBook(){
        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val genre = binding.etGenre.text.toString()
        val synopsis = binding.etSynopsis.text.toString()

        if (title.isNotBlank() && author.isNotBlank() && genre.isNotBlank() && synopsis.isNotBlank()) {
            val updatedBook = Books(title, author, genre, synopsis)
            booksViewModel.editBook(updatedBook)

            booksViewModel.booksList.observe(this, Observer {
                booksAdapter.notifyDataSetChanged()
            })

            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
        else{
            val alertConfirmation : AlertDialog.Builder = AlertDialog.Builder(this)
            alertConfirmation.setMessage("Hubo un error al cargar los datos")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
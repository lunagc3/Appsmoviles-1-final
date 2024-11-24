package com.example.afinal.view.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.afinal.databinding.ItemBooksBinding
import com.example.afinal.model.Books
import com.example.afinal.view.FourthActivity
import com.example.afinal.view.MainActivity
import com.example.afinal.view.SecondActivity
import com.example.afinal.view.ThirdActivity
import com.example.afinal.viewmodel.BooksViewModel

class BooksViewHolder (view : View) : ViewHolder(view) {
    private val binding : ItemBooksBinding = ItemBooksBinding.bind(view)
    fun render(book : Books) {
        binding.tvBookTitle.text = book.title
        binding.tvBookAuthor.text = book.author
        binding.tvBookGenre.text = book.genre
        binding.tvBookSynopsis.text = book.synopsis

        binding.btnEdit.setOnClickListener{
            val intent = Intent(itemView.context, FourthActivity::class.java)
            intent.putExtra("book_title", book.title)
            intent.putExtra("book_author", book.author)
            intent.putExtra("book_genre", book.genre)
            intent.putExtra("book_synopsis", book.synopsis)
            itemView.context.startActivity(intent)

        }
        binding.btnDelete.setOnClickListener{
            val alertDialog = AlertDialog.Builder(itemView.context)
                .setTitle("Confirmar eliminar")
                .setMessage("Realmente quieres borrar este libro?")
                .setPositiveButton("Sí") { _, _ ->
                    if(itemView.context is MainActivity){
                        (itemView.context as MainActivity).deleteBook(book)
                    }else if (itemView.context is ThirdActivity){
                        (itemView.context as ThirdActivity).deleteBook(book)
                    }

                }
                .setNegativeButton("No", null)
                .create()
            alertDialog.show()
        }
    }
}

package com.example.afinal.model

data class Books (
    val title: String,
    val author: String,
    val genre: String,
    val synopsis: String
)
object BookList{
    val listOfBooks = mutableListOf<Books>() }
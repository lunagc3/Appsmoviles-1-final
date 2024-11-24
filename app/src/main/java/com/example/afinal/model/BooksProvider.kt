package com.example.afinal.model

import android.widget.Toast

class BooksProvider {
    companion object{
        val booksList = mutableListOf<Books>(
            Books(
                "Harry Potter y la piedra filosofal", "J.K. Rowling", "Fantasía", "La primera entrega de la saga Harry Potter, que narra las aventuras del joven mago Harry y sus amigos en la escuela de magia Hogwarts."
            ),
            Books(
                "El principito", "Antoine de Saint-Exupéry", "Literatura infantil", "Un piloto se encuentra perdido en el desierto y conoce a un pequeño príncipe proveniente de otro planeta, quien le enseña valiosas lecciones sobre la vida y el amor."
            ),
            Books(
                "Matilda", "Roald Dahl", "Literatura infantil", "Matilda es una niña prodigio con habilidades telequinéticas que usa para enfrentarse a su malvada directora escolar y buscar su propio destino."
            ),
            Books(
                "Las crónicas de Narnia: El león, la bruja y el armario", "C.S. Lewis", "Fantasía", "Cuatro niños descubren un mundo mágico al otro lado de un armario y se unen al león Aslan en una batalla épica contra la malvada Bruja Blanca."
            ),
            Books(
                "Charlie y la fábrica de chocolate", "Roald Dahl", "Literatura infantil", "Charlie gana un boleto dorado para visitar la misteriosa fábrica de chocolate de Willy Wonka, donde descubre maravillas y peligros inesperados."
            ),
            Books(
                "El libro salvaje", "Juan Villoro", "Literatura juvenil", "Juan encuentra un libro mágico que cobra vida y lo lleva a vivir aventuras extraordinarias en un mundo fantástico."
            ),
            Books(
                "Alicia en el país de las maravillas", "Lewis Carroll", "Literatura infantil", "Alicia cae por una madriguera y se encuentra en un mundo surrealista lleno de criaturas extravagantes y situaciones absurdas."
            ),
            Books(
                "La historia interminable", "Michael Ende", "Literatura juvenil", "Bastian descubre un libro mágico que lo transporta al mundo de Fantasía, donde se embarca en una misión para salvar ese universo de la destrucción."
            ),
            Books(
                "Pippi Calzaslargas", "Astrid Lindgren", "Literatura infantil", "Pippi es una niña valiente, fuerte y aventurera que vive sola con su caballo y su mono, y siempre encuentra formas creativas de divertirse y ayudar a los demás."
            ),
            Books(
                "Momo", "Michael Ende", "Literatura juvenil", "Momo es una niña especial que posee la habilidad de escuchar y comprender a las personas. Enfrenta a los hombres grises, quienes roban el tiempo de las personas."
            )
        )
        fun addBook(book: Books){
            booksList.add(book)
        }
        fun editBook(editedBook : Books){
            val index = booksList.indexOfFirst { it.title == editedBook.title }
            if (index != -1) {
                booksList[index] = editedBook
            }
        }
        fun deleteBook(book: Books){
            booksList.remove(book)
        }
    }
}
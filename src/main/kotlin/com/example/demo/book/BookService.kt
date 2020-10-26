package com.example.demo.book

import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.UUID

@Service
class BookService(private val bookRepository: BookRepository) {

  fun listBooks(): Collection<Book> {
    return bookRepository.findAll()
  }

  fun addBook(book: Book): Book {
    return bookRepository.save(book)
  }

  fun updateBook(bookId: UUID, updatedBook: UpdateBookDto): Book {
    val book = bookRepository.findById(bookId).orElseThrow { IllegalArgumentException("not found") }
    updatedBook.author?.let { book.author = updatedBook.author }
    updatedBook.title?.let { book.title = updatedBook.title }
    updatedBook.borrowed?.let { book.borrowed = updatedBook.borrowed }
    return bookRepository.save(book)
  }

  fun deleteBook(bookId: UUID) {
    bookRepository.deleteById(bookId)
  }
}

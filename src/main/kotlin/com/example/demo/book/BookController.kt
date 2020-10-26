package com.example.demo.book

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/books", produces = ["application/json"])
class BookController(private val bookService: BookService) {

  @GetMapping
  fun listBooks(): Collection<Book> {
    return bookService.listBooks()
  }

  @PostMapping
  fun createBook(
    @RequestBody book: Book
  ): Book {
    return bookService.addBook(book)
  }

  @PatchMapping("/{bookId}")
  fun updateBook(
    @PathVariable bookId: UUID,
    @RequestBody updateBookDto: UpdateBookDto
  ): Book {
    return bookService.updateBook(bookId, updateBookDto)
  }

  @DeleteMapping(value = ["/{bookId}"])
  fun deleteBook(
    @PathVariable bookId: UUID
  ) {
    return bookService.deleteBook(bookId)
  }
}

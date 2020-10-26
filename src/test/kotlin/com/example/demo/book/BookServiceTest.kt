package com.example.demo.book

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.reset
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.UUID
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class BookServiceTest(
  @Mock private val bookRepository: BookRepository
) {

  @InjectMocks
  private lateinit var underTest: BookService

  val anyBook = Book(
    author = "Sample author",
    title = "My witty title",
    borrowed = false
  )

  @AfterEach
  fun resetMocks() {
    reset(bookRepository)
  }

  @Test
  fun `Should list books`() {
    // Arrange
    val list = arrayListOf(anyBook)
    given(bookRepository.findAll()).willReturn(list)
    // Act
    val result = underTest.listBooks()

    // Assert
    verify(bookRepository).findAll()
    assertThat(result).isEqualTo(list)
  }

  @Test
  fun `Should add book`() {
    // Arrange
    val storedBook = Book("author", "title")
    given(bookRepository.save(anyBook)).willReturn(storedBook)

    // Act
    val result = underTest.addBook(anyBook)

    // Assert
    verify(bookRepository).save(anyBook)
    assertThat(result).isSameAs(storedBook)
  }

  @Test
  fun `Should delete book`() {
    // Arrange
    val bookId = UUID.randomUUID()

    // Act
    underTest.deleteBook(bookId)

    // Assert
    verify(bookRepository).deleteById(bookId)
  }

  @Test
  fun `Should update book`() {
    // Arrange
    val book = Book("oldAuthor", "oldTitle")
    given(bookRepository.findById(book.id)).willReturn(Optional.of(book))
    given(bookRepository.save(book)).willReturn(book)

    val updateBookDto = UpdateBookDto(author = "newAuthor", title = "newTitle", borrowed = true)

    // Act
    val updatedBook = underTest.updateBook(book.id, updateBookDto)

    // Assert
    assertThat(updatedBook.borrowed).isEqualTo(updateBookDto.borrowed)
    assertThat(updatedBook.author).isEqualTo(updateBookDto.author)
    assertThat(updatedBook.title).isEqualTo(updateBookDto.title)
  }

}

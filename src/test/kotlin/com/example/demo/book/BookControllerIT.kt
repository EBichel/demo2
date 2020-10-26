package com.example.demo.book

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class BookControllerIT : AbstractControllerIT() {

  val anyBook = Book(
    author = "Some witty author",
    title = "Some witty title"
  )

  @Test
  fun `Book GET endpoint should return array of books`() {
    val testBook = bookRepository.save(anyBook)

    unauthenticated().get("/books")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
      .assertThat()
      .body("size()", equalTo(1))
  }

  @Test
  fun `Book POST endpoint should return added book`() {
    unauthenticated()
      .body(anyBook)
      .post("/books")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
  }

  @Test
  fun `Book PATCH endpoint should update book properties`() {
    val testBook = bookRepository.save(anyBook)
    val updateBookDto = UpdateBookDto(
      title = "new title",
      author = "different author",
      borrowed = true
    )

    val extract: Book = unauthenticated()
      .body(updateBookDto)
      .patch("/books/${testBook.id}")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
      .extract().body().`as`(Book::class.java)
    // jsonpath had some issue of extracting values resulting in null

    assertTrue(extract.title == updateBookDto.title)
    assertTrue(extract.author == updateBookDto.author)
    assertTrue(extract.borrowed == updateBookDto.borrowed)
  }

  @Test
  fun `Book DELETE endpoint should delete book with given id`() {
    val testBook = bookRepository.save(anyBook)

    unauthenticated()
      .log().all()
      .delete("/books/${testBook.id}")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())

    assertThat(bookRepository.findById(testBook.id)).isEmpty
  }

}

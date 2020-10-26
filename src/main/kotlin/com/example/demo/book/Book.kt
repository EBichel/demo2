package com.example.demo.book

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "books")
data class Book(
  var author: String,
  var title: String,
  var borrowed: Boolean = false
) {
  @Id
  val id: UUID = UUID.randomUUID()
}

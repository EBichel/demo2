package com.example.demo.book

data class UpdateBookDto(
  val author: String? = null,
  val title: String? = null,
  val borrowed: Boolean? = null
)

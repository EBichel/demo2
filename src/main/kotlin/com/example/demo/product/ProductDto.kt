package com.example.demo.product

import java.util.UUID

data class ProductDto (
  val id: UUID,
  val name: String,
  val duration: Long,
  val netPrice: Long,
  val grossPrice: Long,
  val tax: Long,
  val taxRate: Long
)

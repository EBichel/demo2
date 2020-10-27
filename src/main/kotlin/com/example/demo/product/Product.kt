package com.example.demo.product

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class Product(
  val name: String,
  val duration: Long,
  val netPrice: Long,
  val taxRate: Long
) {
  @Id
  val id: UUID = UUID.randomUUID()
}

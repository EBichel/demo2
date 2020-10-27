package com.example.demo.product

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
import java.util.UUID

@Service
class ProductService(
  private val productRepository: ProductRepository
) {

  fun listProducts(): Collection<Product> {
    return productRepository.findAll()
  }

  fun getProduct(uuid: UUID): Product {
    return productRepository.findById(uuid).orElseThrow { NotFoundException() }
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  class NotFoundException : RuntimeException()
}

package com.example.demo.product

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
import java.util.UUID

@Service
class ProductService(
  private val productRepository: ProductRepository,
  private val productDtoConverter: ProductDtoConverter
) {

  fun listProducts(): Collection<ProductDto> {
    return productRepository.findAll().map { it.toDto() }
  }

  fun getProduct(uuid: UUID): ProductDto {
    return productRepository.findById(uuid)
      .orElseThrow { NotFoundException() }
      .toDto()
  }

  fun Product.toDto(): ProductDto {
    return productDtoConverter.toDto(this)
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  class NotFoundException : RuntimeException()
}

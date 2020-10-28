package com.example.demo.product

import org.springframework.stereotype.Component

@Component
class ProductDtoConverter(
  private val taxCalculator: TaxCalculator
) {
  fun toDto(product: Product): ProductDto {
    val tax = taxCalculator.calculateTax(product)
    return ProductDto(
      id = product.id,
      name = product.name,
      duration = product.duration,
      netPrice = product.netPrice,
      grossPrice = product.netPrice + tax,
      tax = tax,
      taxRate = product.taxRate
    )
  }
}

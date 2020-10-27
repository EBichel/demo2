package com.example.demo.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/products", produces = ["application/json"])
class ProductController(
  private val productService: ProductService
) {

  @GetMapping
  fun listProducts(): Collection<Product> {
    return productService.listProducts()
  }

  @GetMapping("/{productId}")
  fun getProduct(@PathVariable productId: UUID): Product {
    return productService.getProduct(productId)
  }

}

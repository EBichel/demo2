package com.example.demo.product

import org.springframework.stereotype.Component
import kotlin.math.roundToLong

@Component
class TaxCalculator {

  fun calculateTax(product: Product): Long {
    return (product.netPrice.toDouble() * (product.taxRate.toDouble()) / 10000.0).roundToLong()
  }

}

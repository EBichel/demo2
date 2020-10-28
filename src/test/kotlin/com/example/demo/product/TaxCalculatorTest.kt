package com.example.demo.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TaxCalculatorTest {

  private val underTest = TaxCalculator()

  @Test
  fun `calculate tax of valid product`() {
    // Arrange
    val product = Product("test", 100, 10000, 1600)

    // Act
    val tax = underTest.calculateTax(product)

    // Assert
    assertThat(tax).isEqualTo(1600)
  }

  @Test
  fun `check correct rounding of tax of valid product`() {
    // Arrange
    val product = Product("test", 100, 524, 1600)

    // Act
    val tax = underTest.calculateTax(product)

    // Assert
    assertThat(tax).isEqualTo(84)
  }
}

package com.example.demo.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductDtoConverterTest {

  private val underTest = ProductDtoConverter(TaxCalculator())

  @Test
  fun `should create a ProductDto from valid Product`() {
    // Arrange
    val product = Product("test", 100, 10000, 1600)

    // Act
    val resultDto = underTest.toDto(product)

    // Assert
    assertThat(resultDto.id).isEqualTo(product.id)
    assertThat(resultDto.name).isEqualTo(product.name)
    assertThat(resultDto.duration).isEqualTo(product.duration)
    assertThat(resultDto.netPrice).isEqualTo(product.netPrice)
    assertThat(resultDto.taxRate).isEqualTo(product.taxRate)
    assertThat(resultDto.grossPrice).isEqualTo(11600)
    assertThat(resultDto.tax).isEqualTo(1600)
  }
}

package com.example.demo.subscription

import com.example.demo.product.ProductDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.reset
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZoneOffset.UTC
import java.util.*

class SubscriptionDtoConverterTest(
) {

  private val anyProductDto = Mockito.mock(ProductDto::class.java)

  private val underTest = SubscriptionDtoConverter()

  private val anyProductId: UUID = UUID.randomUUID()
  private val anyProductTax = 1600L
  private val anyProductGrossPrice = 11599L
  private val anyProductTaxRate = 1600L
  private val anyProductNetPrice = 9999L

  @BeforeEach
  fun resetMocks() {
    reset(anyProductDto)
  }

  @Test
  fun `convert to SubscriptionDto from valid Subscription`() {
    // Arrange
    given(anyProductDto.id).willReturn(anyProductId)
    given(anyProductDto.tax).willReturn(anyProductTax)
    given(anyProductDto.grossPrice).willReturn(anyProductGrossPrice)
    given(anyProductDto.netPrice).willReturn(anyProductNetPrice)
    given(anyProductDto.taxRate).willReturn(anyProductTaxRate)

    val subscription = Subscription(
      productId = anyProductId
    )
    subscription.endDate = subscription.startDate.plusSeconds(5000)

    // Act
    val resultDto = underTest.toDto(subscription, anyProductDto)

    // Assert
    assertThat(resultDto.active).isEqualTo(subscription.active)
    assertThat(resultDto.paused).isEqualTo(subscription.paused)
    assertThat(resultDto.id).isEqualTo(subscription.id)
    assertThat(resultDto.startDate).isEqualTo(LocalDate.ofInstant(subscription.startDate, UTC))
    assertThat(resultDto.endDate).isEqualTo(LocalDate.ofInstant(subscription.endDate, UTC))
    assertThat(resultDto.tax).isEqualTo(anyProductTax)
    assertThat(resultDto.taxRate).isEqualTo(anyProductTaxRate)
    assertThat(resultDto.grossPrice).isEqualTo(anyProductGrossPrice)
    assertThat(resultDto.netPrice).isEqualTo(anyProductNetPrice)
    assertThat(resultDto.productId).isEqualTo(anyProductId)

  }
}

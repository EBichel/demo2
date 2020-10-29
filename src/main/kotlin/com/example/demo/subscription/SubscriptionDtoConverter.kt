package com.example.demo.subscription

import com.example.demo.product.ProductDto
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneOffset.UTC

@Component
class SubscriptionDtoConverter {
  fun toDto(subscription: Subscription, productDto: ProductDto): SubscriptionDto {
    return SubscriptionDto(
      id = subscription.id,
      productId = subscription.productId,
      startDate = LocalDate.ofInstant(subscription.startDate, UTC),
      endDate = subscription.endDate?.let { LocalDate.ofInstant(subscription.endDate, UTC) },
      active = subscription.active,
      paused = subscription.paused,
      grossPrice = productDto.grossPrice,
      netPrice = productDto.netPrice,
      tax = productDto.tax,
      taxRate = productDto.taxRate
    )
  }
}

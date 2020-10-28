package com.example.demo.subscription

import com.example.demo.product.ProductService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
import java.time.temporal.ChronoUnit
import java.util.UUID

@Service
class SubscriptionService(
  private val subscriptionRepository: SubscriptionRepository,
  private val productService: ProductService
) {

  fun getSubscription(subscriptionId: UUID): Subscription {
    return subscriptionRepository.findById(subscriptionId)
      .orElseThrow { NotFoundException() }
  }

  fun createSubscription(createSubscriptionDto: CreateSubscriptionDto): Subscription {
    val product = productService.getProduct(createSubscriptionDto.productId)
    val subscription =  Subscription(
      productId = product.id
    )
   subscription.endDate = subscription.startDate.plus(product.duration, ChronoUnit.HOURS)
    return subscriptionRepository.save(subscription)
  }

  fun updateSubscription(
    subscriptionId: UUID,
    updateSubscriptionDto: UpdateSubscriptionDto
  ): Subscription {
    val subscription = subscriptionRepository.findById(subscriptionId)
      .orElseThrow { NotFoundException() }

    updateSubscriptionDto.active?.let { subscription.active = it }
    updateSubscriptionDto.paused?.let { subscription.paused = it }

    return subscriptionRepository.save(subscription)
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  class NotFoundException : RuntimeException()
}

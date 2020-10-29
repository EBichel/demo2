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
  private val productService: ProductService,
  private val subscriptionDtoConverter: SubscriptionDtoConverter
) {

  fun getSubscription(subscriptionId: UUID): SubscriptionDto {
    val subscription = subscriptionRepository.findById(subscriptionId)
      .orElseThrow { NotFoundException() }
    val productDto = productService.getProduct(subscription.productId)

    return subscriptionDtoConverter.toDto(subscription, productDto)
  }

  fun createSubscription(createSubscriptionDto: CreateSubscriptionDto): SubscriptionDto {
    val productDto = productService.getProduct(createSubscriptionDto.productId)
    val subscription = Subscription(
      productId = productDto.id
    )
    subscription.endDate = subscription.startDate.plus(productDto.duration, ChronoUnit.HOURS)

    return subscriptionDtoConverter.toDto(subscription, productDto)
  }

  fun updateSubscription(
    subscriptionId: UUID,
    updateSubscriptionDto: UpdateSubscriptionDto
  ): SubscriptionDto {
    val subscription = subscriptionRepository.findById(subscriptionId)
      .orElseThrow { NotFoundException() }
    val productDto = productService.getProduct(subscription.productId)

    updateSubscriptionDto.active?.let { subscription.active = it }
    updateSubscriptionDto.paused?.let { subscription.paused = it }

    val storedSubscription = subscriptionRepository.save(subscription)
    return subscriptionDtoConverter.toDto(storedSubscription, productDto)
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  class NotFoundException : RuntimeException()
}

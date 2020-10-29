package com.example.demo.subscription

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/subscriptions", produces = ["application/json"])
class SubscriptionController(
  private val subscriptionService: SubscriptionService
){

  @GetMapping("/{subscriptionId}")
  fun getSubscription(@PathVariable subscriptionId: UUID): SubscriptionDto {
    return subscriptionService.getSubscription(subscriptionId)
  }

  @PostMapping
  fun createSubscription(
    @RequestBody createSubscriptionDto: CreateSubscriptionDto
  ): SubscriptionDto {
    return subscriptionService.createSubscription(createSubscriptionDto)
  }

  @PatchMapping("/{subscriptionId}")
  fun updateSubscription(
    @PathVariable subscriptionId: UUID,
    @RequestBody updateSubscriptionDto: UpdateSubscriptionDto
  ): SubscriptionDto {
    return subscriptionService.updateSubscription(subscriptionId, updateSubscriptionDto)
  }

}

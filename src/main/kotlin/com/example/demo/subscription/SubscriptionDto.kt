package com.example.demo.subscription

import java.time.LocalDate
import java.util.UUID

data class SubscriptionDto(
  val id: UUID,
  val productId: UUID,
  val startDate: LocalDate,
  val endDate: LocalDate?,
  val active: Boolean,
  val paused: Boolean,
  val grossPrice: Long,
  val netPrice: Long,
  val tax: Long,
  val taxRate: Long
)

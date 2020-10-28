package com.example.demo.subscription

import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "subscriptions")
class Subscription(
  val productId: UUID,
  var endDate: Instant? = null,
  var active  : Boolean = true,
  var paused: Boolean = false
) {
  @Id
  val id: UUID = UUID.randomUUID()
  val startDate : Instant = Instant.now()
}

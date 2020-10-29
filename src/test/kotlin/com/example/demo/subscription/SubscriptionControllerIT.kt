package com.example.demo.subscription

import com.example.demo.AbstractControllerIT
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.util.UUID

class SubscriptionControllerIT : AbstractControllerIT() {

  val anyProductId: UUID = UUID.fromString("15993ed6-d4be-4ebc-b235-18b3cf1ccf6a")

  @Test
  fun `POST subscription should create new subscription`() {
    val createSubscriptionDto = CreateSubscriptionDto(anyProductId)

    unauthenticated()
      .body(createSubscriptionDto)
      .post("/subscriptions")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
      .body("productId", equalTo(anyProductId.toString()))
  }

  @Test
  fun `GET subscription by id should return subscription`() {
    val subscription = subscriptionRepository.save(
      Subscription(anyProductId)
        .also { it.endDate = it.startDate.plusSeconds(5000) }
    )

    unauthenticated()
      .get("/subscriptions/${subscription.id}")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
      .body("productId", equalTo(anyProductId.toString()))
  }

  @Test
  fun `GET subscription of random id should return NOT FOUND`() {
    val subscriptionId = UUID.randomUUID()

    unauthenticated()
      .get("/subscriptions/$subscriptionId")
      .then()
      .log().all()
      .assertThat()
      .statusCode(HttpStatus.NOT_FOUND.value())
  }

  @Test
  fun `PATCH subscription by id return updated subscription`() {
    val subscription = subscriptionRepository.save(
      Subscription(anyProductId)
        .also { it.endDate = it.startDate.plusSeconds(5000) }
    )

    val updateSubscriptionDto = UpdateSubscriptionDto(
      active = false,
      paused = true
    )

    unauthenticated()
      .body(updateSubscriptionDto)
      .patch("/subscriptions/${subscription.id}")
      .then()
      .log().all()
      .assertThat()
      .statusCode(HttpStatus.OK.value())
      .body("active", equalTo(false))
      .body("paused", equalTo(true))
  }
}

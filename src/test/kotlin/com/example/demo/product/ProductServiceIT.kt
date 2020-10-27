package com.example.demo.product

import com.example.demo.AbstractControllerIT
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.util.*

class ProductServiceIT : AbstractControllerIT() {

  @Test
  fun `Products GET endpoint should return array of products`() {
    unauthenticated().get("/products")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
      .assertThat()
      .body("size()", equalTo(2))
  }

  @Test
  fun `Product GET endpoint should return single product`() {
    // hard-coded product id
    val productId = "15993ed6-d4be-4ebc-b235-18b3cf1ccf6a"
    unauthenticated().get("/products/$productId")
      .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
      .assertThat()
      .body("name", equalTo("cheap subscription"))
      .body("duration", equalTo(3600))
      .body("netPrice", equalTo(9999))
      .body("taxRate", equalTo(1600))
  }

  @Test
  fun `Product GET endpoint should return NOT FOUND`() {
    val productId = UUID.randomUUID()
    unauthenticated().get("/products/$productId")
      .then()
      .log().all()
      .statusCode(HttpStatus.NOT_FOUND.value())
  }
}

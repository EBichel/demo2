package com.example.demo

import com.example.demo.subscription.SubscriptionRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

/**
 * Helper class for integration tests
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("integration")
abstract class AbstractControllerIT {

  @Autowired
  lateinit var subscriptionRepository: SubscriptionRepository

  @LocalServerPort
  protected var testPort: Int = -1

  @BeforeEach
  fun before() {
    RestAssured.port = testPort

    subscriptionRepository.deleteAll()
  }

  fun unauthenticated(): RequestSpecification {
    return RestAssured.given()
      .accept(ContentType.JSON)
      .apply { this.contentType(ContentType.JSON) }
  }
}

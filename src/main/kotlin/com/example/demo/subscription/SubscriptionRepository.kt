package com.example.demo.subscription

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SubscriptionRepository : JpaRepository<Subscription, UUID>

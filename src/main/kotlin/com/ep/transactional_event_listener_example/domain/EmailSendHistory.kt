package com.ep.transactional_event_listener_example.domain

import com.ep.transactional_event_listener_example.domain.enums.EmailType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class EmailSendHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val sendAt: LocalDateTime,
    val message: String,
    val targetId: Long,
    val type: EmailType
)
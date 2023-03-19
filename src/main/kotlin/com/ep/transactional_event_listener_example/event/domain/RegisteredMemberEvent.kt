package com.ep.transactional_event_listener_example.event.domain

data class RegisteredMemberEvent(
    val memberId: Long,
    val email: String
)
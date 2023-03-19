package com.ep.transactional_event_listener_example.event

data class RegisteredMemberEvent(
    val memberId: Long,
    val email: String
)
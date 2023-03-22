package com.ep.transactional_event_listener_example.event

data class UpdateMemberEvent(
    val memberId: Long,
    val nickname: String
)
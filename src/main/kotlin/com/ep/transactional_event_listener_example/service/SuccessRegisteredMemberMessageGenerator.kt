package com.ep.transactional_event_listener_example.service

import com.ep.transactional_event_listener_example.service.data.EmailMessage


class SuccessRegisteredMemberMessageGenerator {
    companion object {
        fun generate(memberId: Long) = EmailMessage(memberId = memberId, message = "default message")
    }
}
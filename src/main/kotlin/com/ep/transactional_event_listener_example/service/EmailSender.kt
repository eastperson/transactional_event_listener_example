package com.ep.transactional_event_listener_example.service

import com.ep.transactional_event_listener_example.service.data.EmailMessage
import org.springframework.stereotype.Component

@Component
class EmailSender {
    fun send(successRegisteredMember: EmailMessage?, emailAddress: String?) {

    }
}
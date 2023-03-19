package com.ep.transactional_event_listener_example.event.handler

import com.ep.transactional_event_listener_example.event.domain.RegisteredMemberEvent
import com.ep.transactional_event_listener_example.service.MailService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class RegisteredMemberEventHandler(
    private val mailService: MailService
) {

    @EventListener
    fun sendSuccessMail(registeredMemberEvent: RegisteredMemberEvent) {
        mailService.sendSuccessRegisteredMemberMail(registeredMemberEvent.memberId, registeredMemberEvent.email)
    }
}
package com.ep.transactional_event_listener_example.event

import com.ep.transactional_event_listener_example.service.MailService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RegisteredMemberEventHandler(
    private val mailService: MailService
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun sendSuccessMail(registeredMemberEvent: RegisteredMemberEvent) {
        mailService.sendSuccessRegisteredMemberMail(registeredMemberEvent.memberId, registeredMemberEvent.email)
    }
}
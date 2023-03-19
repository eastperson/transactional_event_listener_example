package com.ep.transactional_event_listener_example.event.handler

import com.ep.transactional_event_listener_example.event.domain.RegisteredMemberEvent
import com.ep.transactional_event_listener_example.service.MailService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionSynchronizationAdapter
import org.springframework.transaction.support.TransactionSynchronizationManager


@Component
class RegisteredMemberEventHandler(
    private val mailService: MailService
) {

    @EventListener
    fun sendSuccessMail(registeredMemberEvent: RegisteredMemberEvent) {
        handleAsynchronously(registeredMemberEvent)
    }

    private fun handleAsynchronously(event: RegisteredMemberEvent) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            println("after commit")
            processAfterCommit(event)
        } else {
            println("now")
            processNow(event)
        }
    }

    private fun processNow(event: RegisteredMemberEvent) {
        mailService.sendSuccessRegisteredMemberMail(event.memberId, event.email)
    }

    private fun processAfterCommit(event: RegisteredMemberEvent) {
        TransactionSynchronizationManager.registerSynchronization(object : TransactionSynchronizationAdapter() {
            override fun afterCommit() {
                mailService.sendSuccessRegisteredMemberMail(event.memberId, event.email)
            }
        })
    }
}
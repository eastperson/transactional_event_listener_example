package com.ep.transactional_event_listener_example.service

import com.ep.transactional_event_listener_example.domain.EmailSendHistory
import com.ep.transactional_event_listener_example.domain.enums.EmailType
import com.ep.transactional_event_listener_example.repository.EmailSendHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class MailService(
    private val emailSender: EmailSender,
    private val emailSendHistoryRepository: EmailSendHistoryRepository
) {

    fun sendSuccessRegisteredMemberMail(memberId: Long?, emailAddress: String) {
        val successRegisteredMember = SuccessRegisteredMemberMessageGenerator.generate(memberId!!)
        emailSender.send(successRegisteredMember, emailAddress)

        val emailSendHistory = EmailSendHistory(
            sendAt = LocalDateTime.now(),
            message = successRegisteredMember.message,
            targetId = memberId,
            type = EmailType.MEMBER_REGISTER_SUCESS
        )
        emailSendHistoryRepository.save(emailSendHistory)
    }
}

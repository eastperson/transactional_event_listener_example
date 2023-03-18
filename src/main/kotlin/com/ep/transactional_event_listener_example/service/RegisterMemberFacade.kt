package com.ep.transactional_event_listener_example.service

import com.ep.transactional_event_listener_example.service.data.RegisterMemberRequestData
import com.ep.transactional_event_listener_example.service.data.RegisterMemberResponseData
import org.springframework.stereotype.Component

@Component
class RegisterMemberFacade(
    private val memberService: MemberService,
    private val mailService: MailService
) {
    fun registerProcess(registerMemberRequestData: RegisterMemberRequestData): RegisterMemberResponseData {

        // 1. 회원 등록
        val savedMember = memberService.register(registerMemberRequestData)

        // 2. 가입 축하 메일 발송 (메일 발송 기록은 db에 저장)
        try {
            mailService.sendSuccessRegisteredMemberMail(savedMember.id, savedMember.email)
        } catch (e: RuntimeException) {
            println("catch runtime exception")
        }

        return RegisterMemberResponseData(memberId = savedMember.id!!)
    }
}
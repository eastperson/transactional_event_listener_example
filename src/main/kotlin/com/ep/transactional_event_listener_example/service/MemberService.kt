package com.ep.transactional_event_listener_example.service

import com.ep.transactional_event_listener_example.domain.Member
import com.ep.transactional_event_listener_example.repository.MemberRepository
import com.ep.transactional_event_listener_example.service.data.RegisterMemberRequestData
import com.ep.transactional_event_listener_example.service.data.RegisterMemberResponseData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val mailService: MailService
) {

    fun registerProcess(registerMemberRequestData: RegisterMemberRequestData): RegisterMemberResponseData {

        // 1. 회원 등록
        val savedMember = register(registerMemberRequestData)

        // 2. 가입 축하 메일 발송 (메일 발송 기록은 db에 저장)
        try {
            mailService.sendSuccessRegisteredMemberMail(savedMember.id, savedMember.email)
        } catch (e: RuntimeException) {
            println("catch runtime exception")
        }

        return RegisterMemberResponseData(memberId = savedMember.id!!)
    }

    private fun register(requestData: RegisterMemberRequestData): Member {
        val newMember = Member(nickname = requestData.nickname, email = requestData.email)
        return memberRepository.save(newMember)
    }
}
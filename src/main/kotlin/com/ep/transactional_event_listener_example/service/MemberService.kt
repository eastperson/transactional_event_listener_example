package com.ep.transactional_event_listener_example.service

import com.ep.transactional_event_listener_example.domain.Member
import com.ep.transactional_event_listener_example.event.RegisteredMemberEvent
import com.ep.transactional_event_listener_example.repository.MemberRepository
import com.ep.transactional_event_listener_example.service.data.RegisterMemberRequestData
import com.ep.transactional_event_listener_example.service.data.RegisterMemberResponseData
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun registerProcess(registerMemberRequestData: RegisterMemberRequestData): RegisterMemberResponseData {

        // 1. 회원 등록
        val savedMember = register(registerMemberRequestData)

        // error: db 에러
        // savedMember.updateNickname("123456789012345678901234567890")

        // 2. 회원 등록 이벤트 발행
        val registeredMemberEvent = RegisteredMemberEvent(savedMember.id!!, savedMember.email!!)
        applicationEventPublisher.publishEvent(registeredMemberEvent)

        return RegisterMemberResponseData(memberId = savedMember.id)
    }

    private fun register(requestData: RegisterMemberRequestData): Member {
        val newMember = Member(nickname = requestData.nickname, email = requestData.email)
        return memberRepository.save(newMember)
    }
}
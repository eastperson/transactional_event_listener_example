package com.ep.transactional_event_listener_example.service

import com.ep.transactional_event_listener_example.domain.Member
import com.ep.transactional_event_listener_example.repository.MemberRepository
import com.ep.transactional_event_listener_example.service.data.RegisterMemberRequestData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun register(requestData: RegisterMemberRequestData): Member {
        val newMember = Member(nickname = requestData.nickname, email = requestData.email)
        return memberRepository.save(newMember)
    }
}
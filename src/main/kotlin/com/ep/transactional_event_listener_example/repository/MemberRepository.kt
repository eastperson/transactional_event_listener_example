package com.ep.transactional_event_listener_example.repository

import com.ep.transactional_event_listener_example.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>
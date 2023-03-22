package com.ep.transactional_event_listener_example.domain

import com.ep.transactional_event_listener_example.event.UpdateMemberEvent
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.domain.DomainEvents

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(columnDefinition = "varchar(20)")
    var nickname: String? = null,
    val email: String? = null
) : AbstractAggregateRoot<Member>() {

    fun updateNickname(newNickname: String) {
        this.nickname = newNickname
        registerEvent(UpdateMemberEvent(memberId = this.id!!, nickname = newNickname))
    }
}

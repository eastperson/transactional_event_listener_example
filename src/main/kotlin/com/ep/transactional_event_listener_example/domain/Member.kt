package com.ep.transactional_event_listener_example.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(columnDefinition = "varchar(20)")
    var nickname: String? = null,
    val email: String? = null
) {
    fun updateNickname(newNickname: String) {
        this.nickname = newNickname
    }
}

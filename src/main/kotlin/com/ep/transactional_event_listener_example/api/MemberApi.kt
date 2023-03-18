package com.ep.transactional_event_listener_example.api

import com.ep.transactional_event_listener_example.service.RegisterMemberFacade
import com.ep.transactional_event_listener_example.service.data.RegisterMemberRequestData
import com.ep.transactional_event_listener_example.service.data.RegisterMemberResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberApi(
    private val registerMemberFacade: RegisterMemberFacade
) {

    @PostMapping
    fun register(@RequestBody request: RegisterMemberRequestData): ResponseEntity<RegisterMemberResponseData> {
        return ResponseEntity.ok(registerMemberFacade.registerProcess(request))
    }
}
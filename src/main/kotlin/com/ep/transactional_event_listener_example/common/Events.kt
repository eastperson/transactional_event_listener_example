package com.ep.transactional_event_listener_example.common

import org.springframework.context.ApplicationEventPublisher

object Events {
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    fun setPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher
    }

    fun raise(event: Any) {
        this.applicationEventPublisher.publishEvent(event)
    }
}
package com.ep.transactional_event_listener_example.common

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration

@Configuration
class EventsConfiguration(
    private val applicationContext: ApplicationContext
) : InitializingBean {

    override fun afterPropertiesSet() {
        Events.setPublisher(applicationContext)
    }
}
package com.ep.transactional_event_listener_example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class TransactionalEventListenerExampleApplication

fun main(args: Array<String>) {
    runApplication<TransactionalEventListenerExampleApplication>(*args)
}

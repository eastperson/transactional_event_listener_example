package com.ep.transactional_event_listener_example.repository

import com.ep.transactional_event_listener_example.domain.EmailSendHistory
import org.springframework.data.jpa.repository.JpaRepository

interface EmailSendHistoryRepository : JpaRepository<EmailSendHistory, Long>
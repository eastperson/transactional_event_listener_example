package com.ep.transactional_event_listener_example

import com.ep.transactional_event_listener_example.service.MemberService
import com.ep.transactional_event_listener_example.service.data.RegisterMemberRequestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.MySQLContainer

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ContextConfiguration(initializers = [TransactionalEventListenerExampleTest.Companion.Initializer::class])
class TransactionalEventListenerExampleTest {

    companion object {

        @JvmField
        @ClassRule
        val mySQLContainer = MySQLContainer<Nothing>("mysql:8.0.19").apply {
            withDatabaseName("transaction-container")
            withUsername("ep")
            withPassword("1234")
        }

        class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(applicationContext: ConfigurableApplicationContext) {
                TestPropertyValues.of(
                    "spring.datasource.url=${mySQLContainer.jdbcUrl}",
                    "spring.datasource.username=${mySQLContainer.username}",
                    "spring.datasource.password=${mySQLContainer.password}"
                ).applyTo(applicationContext.environment)
            }
        }

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            mySQLContainer.start()
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            mySQLContainer.stop()
        }
    }

    @Autowired
    private lateinit var memberService: MemberService

    @Test
    fun `case 1 - 기본 예제`() {
        val memberRequestData = RegisterMemberRequestData(nickname = "ep", email = "ep@email.com")
        assertThrows<RuntimeException> {
            val response = memberService.registerProcess(memberRequestData)
            assertThat(response).isNotNull
            assertThat(response.memberId).isEqualTo(1L)
        }
    }
}

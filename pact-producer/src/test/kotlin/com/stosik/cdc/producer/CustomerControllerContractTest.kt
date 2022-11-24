package com.stosik.cdc.producer

import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider
import com.ninjasquad.springmockk.MockkBean
import com.stosik.cdc.producer.controller.CustomerController
import com.stosik.cdc.producer.controller.CustomerResponse
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc


@WebMvcTest(controllers = [CustomerController::class])
@Provider("Customer API Server")
//@PactFolder("../pact-consumer/build/pacts")
@PactBroker
@ExtendWith(PactVerificationSpringProvider::class)
internal class SampleApiControllerContractTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun beforeEach(context: PactVerificationContext) {
        context.target = MockMvcTestTarget(mockMvc)
    }

    @TestTemplate
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @State("Initial State")
    fun `initial state`() {
        every { customerRepository.save(any()) } returns "1"
        every { customerRepository.get(any()) } returns null
    }

    @State("Customer with id 1 exists")
    fun `should return customer with id 1`() {
        every {
            customerRepository.get("1")
        } returns CustomerResponse("1", "Chris", "Froome")
    }
}
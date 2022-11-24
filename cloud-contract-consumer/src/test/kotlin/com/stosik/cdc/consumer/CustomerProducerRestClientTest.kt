package com.stosik.cdc.consumer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode

@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(
    ids = ["com.stosik.cdc.producer:cloud-contract-producer:+:stubs:8081"],
    stubsMode = StubsMode.LOCAL
)
internal class CustomerProducerRestClientTest {

    @Autowired
    private lateinit var customerProducerRestClient: CustomerProducerRestClient

    @Test
    fun `should get all customers`() {
        val response = customerProducerRestClient.getAllCustomers()

        assertEquals(3, response.customers.size)
    }

    @Test
    fun `should get customer by id`() {
        val response = customerProducerRestClient.getCustomerById("1")

        assertEquals("1", response.id)
        assertEquals("Chris", response.name)
        assertEquals("Froome", response.surname)
    }
}
package com.stosik.cdc.consumer

import assertk.assertThat
import assertk.assertions.isEqualTo
import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.consumer.junit5.ProviderType.SYNCH
import au.com.dius.pact.core.model.V4Pact
import au.com.dius.pact.core.model.annotations.Pact
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.web.client.RestTemplate

@ExtendWith(PactConsumerTestExt::class)
@TestInstance(PER_CLASS)
internal class CustomerContractTest {

    companion object {
        private val restTemplate = RestTemplate()
        private val CUSTOMER_1 = CustomerProducerRestResponse("1", "Chris", "Froome")
        private val CUSTOMER_1_PACT = PactDslJsonBody()
            .stringMatcher("id", "\\w+", "1")
            .stringMatcher("name", "\\w+", "Chris")
            .stringMatcher("surname", "\\w+", "Froome")

        private const val CUSTOMER_ID = "1"
    }

    @Pact(provider = "Customer API Server", consumer = "Customer API Client")
    fun getCustomerById(builder: PactDslWithProvider): V4Pact {
        return builder
            .given("Customer with id 1 exists")
            .uponReceiving("Get customer 1 when exists")
            .path("/customers/1")
            .method("GET")
            .willRespondWith()
            .status(200)
            .headers(mapOf("Content-Type" to "application/json"))
            .body(CUSTOMER_1_PACT)
            .toPact(V4Pact::class.java)
    }

    @Test
    @PactTestFor(providerName = "Customer API Server", pactMethod = "getCustomerById", providerType = SYNCH)
    fun `should get customer 1 when it exists`(mockServer: MockServer) {
        val client = CustomerProducerRestClient("${mockServer.getUrl()}/customers", restTemplate)
        val customer = client.getCustomerById(CUSTOMER_ID)

        assertThat(customer).isEqualTo(CUSTOMER_1)
    }
}
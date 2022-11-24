package com.stosik.cdc.consumer

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
internal class CustomerProducerRestClient(private val restTemplate: RestTemplate) {

    fun getAllCustomers(): CustomersProducerRestResponse {
        val response = restTemplate.getForEntity(baseUrl, CustomersProducerRestResponse::class.java)

        return response.body ?: throw CustomerServerException()
    }

    fun getCustomerById(id: String): CustomerProducerRestResponse {
        val response = restTemplate.getForEntity("$baseUrl/$id", CustomerProducerRestResponse::class.java)

        return response.body ?: throw CustomerServerException()
    }

    companion object {
        private const val baseUrl = "http://localhost:8081/customers"
    }
}

data class CustomersProducerRestResponse(val customers: List<CustomerProducerRestResponse>)
data class CustomerProducerRestResponse(val id: String, val name: String, val surname: String)
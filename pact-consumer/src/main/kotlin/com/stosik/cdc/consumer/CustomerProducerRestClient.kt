package com.stosik.cdc.consumer

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
internal class CustomerProducerRestClient(
    @Value("\${customer.url-path}") private val urlPath: String,
    private val restTemplate: RestTemplate
) {

    fun getAllCustomers(): CustomersProducerRestResponse {
        val response = restTemplate.getForEntity(urlPath, CustomersProducerRestResponse::class.java)

        return response.body ?: throw CustomerServerException()
    }

    fun getCustomerById(id: String): CustomerProducerRestResponse {
        val response = restTemplate.getForEntity("$urlPath/$id", CustomerProducerRestResponse::class.java)

        return response.body ?: throw CustomerServerException()
    }
}

data class CustomersProducerRestResponse(val customers: List<CustomerProducerRestResponse>)
data class CustomerProducerRestResponse(val id: String, val name: String, val surname: String)
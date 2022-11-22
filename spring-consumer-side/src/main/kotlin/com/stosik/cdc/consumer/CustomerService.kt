package com.stosik.cdc.consumer

import org.springframework.stereotype.Service

@Service
internal class CustomerService(private val customerRestClient: CustomerProducerRestClient) {

    fun findAllCustomers(): CustomersConsumerRestResponse {
        return CustomersConsumerRestResponse.from(customerRestClient.getAllCustomers())
    }

    fun findCustomerById(id: String): CustomerConsumerRestResponse {
        return CustomerConsumerRestResponse.from(customerRestClient.getCustomerById(id))
    }
}

data class CustomersConsumerRestResponse(val customers: List<CustomerConsumerRestResponse>) {
    companion object Factory {
        fun from(response: CustomersProducerRestResponse) = response
            .customers
            .map { CustomerConsumerRestResponse.from(it) }
            .let { CustomersConsumerRestResponse(it) }
    }
}

data class CustomerConsumerRestResponse(val id: String, val name: String, val surname: String) {
    companion object Factory {
        fun from(response: CustomerProducerRestResponse): CustomerConsumerRestResponse {
            return CustomerConsumerRestResponse(response.id, response.name, response.surname)
        }
    }
}
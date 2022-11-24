package com.stosik.cdc.producer

import com.stosik.cdc.producer.controller.CustomerResponse
import org.springframework.stereotype.Repository

@Repository
internal class CustomerRepository {

    private val customers = mutableMapOf<String, CustomerResponse>()

    fun save(customer: CustomerResponse): String {
        customers[customer.id] = customer
        return customer.id
    }

    fun get(customerId: String) = customers[customerId]

    fun reset() {
        customers.clear()
    }
}
package com.stosik.cdc.consumer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
internal class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    fun getAllCustomers(): CustomersConsumerRestResponse {
        return customerService.findAllCustomers()
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: String): CustomerConsumerRestResponse {
        return customerService.findCustomerById(id)
    }
}
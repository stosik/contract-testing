package com.stosik.cdc.producer.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
internal class CustomerController {

    @GetMapping
    fun getAllCustomers(): CustomersResponse {
        return CustomersResponse(CUSTOMERS.values.toList())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): ResponseEntity<CustomerResponse> {
        return CUSTOMERS[id]
            ?.let { ResponseEntity.ok().body(it) }
            ?: ResponseEntity.notFound().build()
    }
}

data class CustomersResponse(val customers: List<CustomerResponse>)

data class CustomerResponse(val id: String, val name: String, val surname: String)

private val CUSTOMERS = mapOf(
    "1" to CustomerResponse("1", "Chris", "Froome"),
    "2" to CustomerResponse("2", "Tadej", "Pogacar"),
    "3" to CustomerResponse("3", "Jonas", "Vingegaard")
)
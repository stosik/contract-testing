package com.stosik.cdc.producer.controller

import com.stosik.cdc.producer.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
internal class CustomerController(private val customerRepository: CustomerRepository) {

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): ResponseEntity<CustomerResponse> {
        return customerRepository.get(id)
            ?.let { ResponseEntity.ok().body(it) }
            ?: ResponseEntity.notFound().build()
    }
}

data class CustomerResponse(val id: String, val name: String, val surname: String)
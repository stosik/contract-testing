package com.stosik.cdc.producer

import com.stosik.cdc.producer.controller.CustomerController
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [CustomerController::class])
internal class CustomerControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should get all customers`() {
        mockMvc.perform(
            get("/customers").contentType("application/json")
        ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.customers", hasSize<Any>(3)))
            .andExpect(jsonPath("$.customers[0].id").value("1"))
            .andExpect(jsonPath("$.customers[0].name").value("Chris"))
            .andExpect(jsonPath("$.customers[0].surname").value("Froome"))
    }

    @Test
    fun `should get customer by id`() {
        mockMvc.perform(
            get("/customers/1").contentType("application/json")
        ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Chris"))
            .andExpect(jsonPath("$.surname").value("Froome"))
    }
}
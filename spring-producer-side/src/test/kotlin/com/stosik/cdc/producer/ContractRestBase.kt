package com.stosik.cdc.producer

import com.stosik.cdc.producer.controller.CustomerController
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier
import org.springframework.test.annotation.DirtiesContext


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
abstract class ContractRestBase {

    @Autowired
    private lateinit var customerController: CustomerController

    @BeforeEach
    fun beforeEach() {
        RestAssuredMockMvc.standaloneSetup(customerController)
    }
}
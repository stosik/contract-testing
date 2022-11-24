package com.stosik.cdc.consumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
internal class RestTemplateConfiguration {

    @Bean
    fun restTemplate() = RestTemplate()
}
package io.github.artemptushkin.breakingbad.pact.provider

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class JessePinkmanApplication {
    @Bean
    fun restTemplate(@Value("\${heisenberg.port}") port: Int): RestTemplate = RestTemplateBuilder()
        .rootUri("http://localhost:$port")
        .build()
}

fun main(args: Array<String>) {
    runApplication<JessePinkmanApplication>(*args)
}

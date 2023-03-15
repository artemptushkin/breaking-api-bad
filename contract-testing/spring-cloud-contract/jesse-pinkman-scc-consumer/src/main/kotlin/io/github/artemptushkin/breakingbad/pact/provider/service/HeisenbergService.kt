package io.github.artemptushkin.breakingbad.pact.provider.service

import io.github.artemptushkin.breakingbad.pact.provider.service.domain.HeisenbergResponse
import io.github.artemptushkin.breakingbad.pact.provider.service.domain.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class HeisenbergService(private val restTemplate: RestTemplate) {

    fun cookCrystals(amount: Int): HeisenbergResponse {
        val heisenbergResponseEntity: ResponseEntity<HeisenbergResponse> = restTemplate
            .getForEntity(
                "/heisenberg/v1/crystals?amount={0}",
                HeisenbergResponse::class.java,
                amount
            )
        return heisenbergResponseEntity.body ?: throw NotFoundException()
    }
}
package io.github.artemptushin.breakingbad.pact.provider.service

import au.com.dius.pact.consumer.dsl.LambdaDsl
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.V4Pact
import au.com.dius.pact.core.model.annotations.Pact
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.RestTemplate

@PactTestFor(port = "8091", providerName = "heisenberg")
@ExtendWith(value = [SpringExtension::class, PactConsumerTestExt::class])
class HeisenbergServiceConsumerContractTest {

    lateinit var heisenbergService: HeisenbergService

    @BeforeEach
    fun setup() {
        heisenbergService = HeisenbergService(RestTemplate())
    }

    @Pact(consumer = "jesse-pinkman")
    fun crystalsPact(builder: PactDslWithProvider): V4Pact {
        return builder
            .given("this guy cooks!")
            .uponReceiving("Crystals, beach!")
            .method("GET")
            .path("/heisenberg/v1/crystals")
            .matchQuery("amount", ".d*", "2")
            .willRespondWith()
            .body(
                LambdaDsl.newJsonBody { bodyDsl ->
                    bodyDsl
                        .`object`("blue") { blue ->
                            blue
                                .numberType("amount", 20.0)
                                .numberType("id", 2)
                        }
                }.build()
            )
            .status(200)
            .toPact(V4Pact::class.java)
    }

    @Test
    fun `this-guy-cooks!`() {
        val crystals = heisenbergService.cookCrystals(2)

        assertThat(crystals).isNotNull
        assertThat(crystals.blue).isNotNull
    }
}
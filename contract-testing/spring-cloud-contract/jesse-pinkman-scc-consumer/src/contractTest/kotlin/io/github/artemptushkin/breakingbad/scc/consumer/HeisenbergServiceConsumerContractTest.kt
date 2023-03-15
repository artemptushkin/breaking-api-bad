package io.github.artemptushkin.breakingbad.scc.consumer

import io.github.artemptushkin.breakingbad.pact.provider.JessePinkmanApplication
import io.github.artemptushkin.breakingbad.pact.provider.service.HeisenbergService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * do not copy this approach, this is not production code proof
 */
@ExtendWith(value = [SpringExtension::class])
@AutoConfigureStubRunner(
    stubsMode = StubRunnerProperties.StubsMode.LOCAL,
    ids = [
        "io.github.artemptushkin.breakingbad:heisenberg-scc-provider:0.0.1"
    ]
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = [JessePinkmanApplication::class])
class HeisenbergServiceConsumerContractTest {

    lateinit var heisenbergService: HeisenbergService
    @StubRunnerPort("io.github.artemptushkin.breakingbad:heisenberg-scc-provider")
    var port: Int = 0

    @BeforeEach
    fun setup() {
        heisenbergService = HeisenbergService(RestTemplateBuilder().rootUri("http://localhost:$port").build())
    }

    @Test
    fun `this-guy-cooks!`() {
        val crystals = heisenbergService.cookCrystals(2)

        assertThat(crystals).isNotNull
        assertThat(crystals.blue).isNotNull
    }
}
package io.github.artemptushin.breakingbad.scc

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(classes = [HeisenbergApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(value = [SpringExtension::class])
abstract class BaseMockMvcTest {
    @Autowired
    lateinit var applicationContext: WebApplicationContext
    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port = port
    }
}
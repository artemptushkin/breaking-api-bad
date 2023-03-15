package io.github.artemptushkin.breakingbad.pact.provider.controller

import io.github.artemptushkin.breakingbad.pact.provider.controller.domain.Crystal
import io.github.artemptushkin.breakingbad.pact.provider.controller.domain.CrystalsResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/v1/crystals")
class CrystalController {
    @GetMapping
    operator fun get(@RequestParam amount: Int): CrystalsResponse {
        val price = BigDecimal("10.0")
        return CrystalsResponse(
            Crystal(1L, price.multiply(BigDecimal.valueOf(amount.toLong())))
        )
    }
}
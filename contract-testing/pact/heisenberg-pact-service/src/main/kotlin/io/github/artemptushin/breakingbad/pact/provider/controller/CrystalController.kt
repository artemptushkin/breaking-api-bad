package io.github.artemptushin.breakingbad.pact.provider.controller

import io.github.artemptushin.breakingbad.pact.provider.controller.domain.Crystal
import io.github.artemptushin.breakingbad.pact.provider.controller.domain.CrystalsResponse
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
        val crystals: List<Crystal> = listOf(Crystal(1L, "red"), Crystal(2L, "blue"))
        return CrystalsResponse(crystals, price.multiply(BigDecimal.valueOf(amount.toLong())))
    }
}
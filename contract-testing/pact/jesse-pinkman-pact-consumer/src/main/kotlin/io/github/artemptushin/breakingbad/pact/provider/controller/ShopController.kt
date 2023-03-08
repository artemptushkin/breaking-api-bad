package io.github.artemptushin.breakingbad.pact.provider.controller

import io.github.artemptushin.breakingbad.pact.provider.controller.domain.CrystalsShopResponse
import io.github.artemptushin.breakingbad.pact.provider.controller.domain.ShopRequest
import io.github.artemptushin.breakingbad.pact.provider.service.HeisenbergService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal


@RestController
@RequestMapping("/v1/shop")
class ShopController(private val heisenbergService: HeisenbergService) {

    @PostMapping
    fun buy(@RequestBody shopRequest: ShopRequest): CrystalsShopResponse {
        val crystalsResponse = heisenbergService.cookCrystals(shopRequest.amount)
        return CrystalsShopResponse(
            amount = crystalsResponse.amount,
            crystals = crystalsResponse.crystals,
            cost = crystalsResponse.amount.multiply(BigDecimal.valueOf(5))
        )
    }
}
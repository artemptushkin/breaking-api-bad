package io.github.artemptushkin.breakingbad.pact.provider.controller

import io.github.artemptushkin.breakingbad.pact.provider.controller.domain.CrystalsShopResponse
import io.github.artemptushkin.breakingbad.pact.provider.controller.domain.ShopRequest
import io.github.artemptushkin.breakingbad.pact.provider.service.HeisenbergService
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
            blue = crystalsResponse.blue,
            cost = crystalsResponse.blue.amount.multiply(BigDecimal.valueOf(5))
        )
    }
}
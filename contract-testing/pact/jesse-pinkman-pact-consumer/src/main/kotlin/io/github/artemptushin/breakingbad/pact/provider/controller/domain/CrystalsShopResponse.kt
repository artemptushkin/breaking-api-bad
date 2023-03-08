package io.github.artemptushin.breakingbad.pact.provider.controller.domain

import java.math.BigDecimal

data class CrystalsShopResponse (
    val amount: BigDecimal,
    val cost: BigDecimal,
    val crystals: List<Crystal>
)
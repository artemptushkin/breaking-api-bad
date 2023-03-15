package io.github.artemptushkin.breakingbad.pact.provider.controller.domain

import java.math.BigDecimal

data class CrystalsShopResponse (
    val cost: BigDecimal,
    val blue: Crystal
)
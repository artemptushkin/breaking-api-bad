package io.github.artemptushin.breakingbad.pact.provider.controller.domain

import java.math.BigDecimal

data class CrystalsResponse (
    val crystals: List<Crystal>,
    val amount: BigDecimal
)
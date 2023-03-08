package io.github.artemptushin.breakingbad.pact.provider.service.domain

import io.github.artemptushin.breakingbad.pact.provider.controller.domain.Crystal
import java.math.BigDecimal

data class HeisenbergResponse (
    val crystals: List<Crystal>,
    val amount: BigDecimal
)
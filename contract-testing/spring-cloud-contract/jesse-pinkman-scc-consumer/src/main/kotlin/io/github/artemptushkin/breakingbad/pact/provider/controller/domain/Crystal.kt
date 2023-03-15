package io.github.artemptushkin.breakingbad.pact.provider.controller.domain

import java.math.BigDecimal

data class Crystal(
    var id: Long,
    val amount: BigDecimal
)
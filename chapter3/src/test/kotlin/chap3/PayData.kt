package chap3

import java.time.LocalDate

data class PayData(
    val firstBillingDate: LocalDate?,
    val billingDate: LocalDate,
    val payAmount: Int
)

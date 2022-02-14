package chap3

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(billingDate: LocalDate, payAmount: Int) =
        billingDate.plusMonths(1)

}

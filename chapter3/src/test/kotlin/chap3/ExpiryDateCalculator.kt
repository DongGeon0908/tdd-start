package chap3

import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {


    fun calculateExpiryDate(payData: PayData): LocalDate? {
        var addedMonths = payData.payAmount / 10000L

        if (payData.payAmount == 100000) {
            addedMonths = 12
        }

        if (payData.firstBillingDate != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths)
        } else {
            return payData.billingDate.plusMonths(addedMonths)
        }
    }

    private fun expiryDateUsingFirstBillingDate(
        payData: PayData,
        addedMonths: Long
    ): LocalDate? {
        val candidateExp = payData.billingDate.plusMonths(addedMonths)
        val dayOfFirstBilling = payData.firstBillingDate!!.dayOfMonth
        if (isSameDayOfMonth(dayOfFirstBilling, candidateExp)) {
            val dayLenOfCandiMon = lastDayOfMonth(candidateExp)

            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon)
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling)
        } else {
            return candidateExp
        }
    }

    private fun lastDayOfMonth(candidateExp: LocalDate?) = YearMonth.from(candidateExp).lengthOfMonth()

    private fun isSameDayOfMonth(dayOfFirstBilling: Int, candidateExp: LocalDate) =
        dayOfFirstBilling != candidateExp.dayOfMonth
}

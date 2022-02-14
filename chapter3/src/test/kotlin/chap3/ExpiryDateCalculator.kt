package chap3

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData) =
        payData.billingDate.plusMonths(1)

}

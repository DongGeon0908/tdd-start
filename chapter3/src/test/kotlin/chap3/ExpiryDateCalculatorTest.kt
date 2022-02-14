package chap3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ExpiryDateCalculatorTest {
    @Test
    fun 만원_납부하면_한달_뒤가_만료() {
        val billingDate = LocalDate.of(2019, 3, 1)
        val payAmount = 10000

        val cal = ExpiryDateCalculator()
        val expiryDate = cal.calculateExpiryDate(billingDate, payAmount)

        assertEquals(LocalDate.of(2019, 4, 1), expiryDate)

        val billingDate2 = LocalDate.of(2019, 5, 5)
        val payAmount2 = 10000

        val cal2 = ExpiryDateCalculator()
        val expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2)

        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2)
    }
}
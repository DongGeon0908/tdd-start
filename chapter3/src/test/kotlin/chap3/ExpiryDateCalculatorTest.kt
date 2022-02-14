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
    }
}
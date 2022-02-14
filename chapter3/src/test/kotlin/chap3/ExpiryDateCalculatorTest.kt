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
        val expiryDate = cal.calculateExpiryDate(PayData(billingDate, payAmount))

        assertEquals(LocalDate.of(2019, 4, 1), expiryDate)

        val billingDate2 = LocalDate.of(2019, 5, 5)
        val payAmount2 = 10000

        val cal2 = ExpiryDateCalculator()
        val expiryDate2 = cal2.calculateExpiryDate(PayData(billingDate2, payAmount2))

        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2)

        assertExpiryDate(
            PayData(
                LocalDate.of(2019, 3, 1),
                10000
            ),
            LocalDate.of(2019, 4, 1)
        )

        assertExpiryDate(
            PayData(
                LocalDate.of(2019, 5, 5),
                10000
            ),
            LocalDate.of(2019, 6, 5)
        )
    }

    @Test
    fun 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
            PayData(
                LocalDate.of(2019, 1, 31),
                10000
            ),
            LocalDate.of(2019, 2, 28)
        )
        assertExpiryDate(
            PayData(
                LocalDate.of(2019, 5, 31),
                10000
            ),
            LocalDate.of(2019, 6, 30)
        )
        assertExpiryDate(
            PayData(
                LocalDate.of(2020, 1, 31),
                10000
            ),
            LocalDate.of(2020, 2, 29)
        )
    }

    fun assertExpiryDate(payData: PayData, expectedExpiryDate: LocalDate) {
        val cal = ExpiryDateCalculator()
        val realExpiryDate = cal.calculateExpiryDate(payData)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}
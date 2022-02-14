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
        val expiryDate = cal.calculateExpiryDate(PayData(null, billingDate, payAmount))

        assertEquals(LocalDate.of(2019, 4, 1), expiryDate)

        val billingDate2 = LocalDate.of(2019, 5, 5)
        val payAmount2 = 10000

        val cal2 = ExpiryDateCalculator()
        val expiryDate2 = cal2.calculateExpiryDate(PayData(null, billingDate2, payAmount2))

        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2)

        assertExpiryDate(
            PayData(
                null,
                LocalDate.of(2019, 3, 1),
                10000
            ),
            LocalDate.of(2019, 4, 1)
        )

        assertExpiryDate(
            PayData(
                null,
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
                null,
                LocalDate.of(2019, 1, 31),
                10000
            ),
            LocalDate.of(2019, 2, 28)
        )
        assertExpiryDate(
            PayData(
                null,
                LocalDate.of(2019, 5, 31),
                10000
            ),
            LocalDate.of(2019, 6, 30)
        )
        assertExpiryDate(
            PayData(
                null,
                LocalDate.of(2020, 1, 31),
                10000
            ),
            LocalDate.of(2020, 2, 29)
        )
    }

    @Test
    fun 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        val payData = PayData(
            LocalDate.of(2019, 1, 31),
            LocalDate.of(2019, 2, 28),
            10000
        )

        val payData2 = PayData(
            LocalDate.of(2019, 1, 30),
            LocalDate.of(2019, 2, 28),
            10000
        )

        val payData3 = PayData(
            LocalDate.of(2019, 5, 31),
            LocalDate.of(2019, 6, 30),
            10000
        )

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31))
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30))
        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31))
    }

    @Test
    fun 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(
            PayData(
                null,
                LocalDate.of(2019, 3, 1),
                20000
            ),
            LocalDate.of(2019, 5, 1)
        )
    }

    @Test
    fun 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(
            PayData(
                LocalDate.of(2019, 1, 31),
                LocalDate.of(2019, 2, 28),
                20000
            ),
            LocalDate.of(2019, 4, 30)
        )
        assertExpiryDate(
            PayData(
                LocalDate.of(2019, 3, 31),
                LocalDate.of(2019, 4, 30),
                30000
            ),
            LocalDate.of(2019, 7, 31)
        )
    }

    @Test
    fun 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
            PayData(
                null,
                LocalDate.of(2019, 1, 28),
                100000
            ),
            LocalDate.of(2020, 1, 28)
        )
    }

    fun assertExpiryDate(payData: PayData, expectedExpiryDate: LocalDate) {
        val cal = ExpiryDateCalculator()
        val realExpiryDate = cal.calculateExpiryDate(payData)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}
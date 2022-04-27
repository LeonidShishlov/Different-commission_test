import org.junit.Test

import org.junit.Assert.*

class Different_commissionKtTest {

    @Test
    fun main() {
        val input = 100
        val input1 = 1




    }

    @Test
    fun calculateCommission_MIR() {
        val kopeck = 10000
        val card = TypesOfPayments.MIR
        val amount = 0

        val result = calculateCommission(
            inputKopeck = kopeck,
            typesOfPayments = card,
            amountOfPreviousTransfers = amount)

        assertEquals(3500, result)


    }
    @Test
    fun calculateCommission_MIR_v2() {
        val kopeck = 500000
        val card = TypesOfPayments.MIR
        val amount = 0

        val result = calculateCommission(
            inputKopeck = kopeck,
            typesOfPayments = card,
            amountOfPreviousTransfers = amount)

        assertEquals(3750, result)


    }

    @Test
    fun calculateCommission_Maestro() {
        val kopeck = 10000
        val card = TypesOfPayments.MAESTRO
        val amount = 0

        val result = calculateCommission(
            inputKopeck = kopeck,
            typesOfPayments = card,
            amountOfPreviousTransfers = amount)

        assertEquals(0, result)


    }
    @Test
    fun calculateCommission_Maestro_v2() {
        val kopeck = 10000
        val card = TypesOfPayments.MAESTRO
        val amount = 76_000_00

        val result = calculateCommission(
            inputKopeck = kopeck,
            typesOfPayments = card,
            amountOfPreviousTransfers = amount)

        assertEquals(2060, result)


    }
    @Test
    fun calculateCommission_Maestro_v3() {
        val kopeck = 76_000_00
        val card = TypesOfPayments.MAESTRO
        val amount = 74_000_00

        val result = calculateCommission(
            inputKopeck = kopeck,
            typesOfPayments = card,
            amountOfPreviousTransfers = amount)

        assertEquals(47600, result)


    }
    @Test
    fun calculateCommission_Maestro_v4() {
        val kopeck = 76_000_00
        val card = TypesOfPayments.MAESTRO
        val amount = 76_000_00

        val result = calculateCommission(
            inputKopeck = kopeck,
            typesOfPayments = card,
            amountOfPreviousTransfers = amount)

        assertEquals(47600, result)


    }
    @Test
    fun calculateCommission_VK_PAY() {
        val kopeck = 10000
        val card = TypesOfPayments.VK_PAY
        val amount = 0

        val result = calculateCommission(
            inputKopeck = kopeck,
            typesOfPayments = card,
            amountOfPreviousTransfers = amount)

        assertEquals(0, result)


    }


    @Test
    fun limitCheck_1_false() {
        val input = 1
        val kopeck = 160_000_00
        val amount = 500_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }

    @Test
    fun limitCheck_2_false() {
        val input = 2
        val kopeck = 160_000_00
        val amount = 500_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }
    @Test
    fun limitCheck_3_false() {
        val input = 3
        val kopeck = 160_000_00
        val amount = 500_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }
    @Test
    fun limitCheck_4_false() {
        val input = 4
        val kopeck = 160_000_00
        val amount = 500_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }
    @Test
    fun limitCheck_5_false_v1() {
        val input = 5
        val kopeck = 16_000_00
        val amount = 39_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }
    @Test
    fun limitCheck_5_false_v2() {
        val input = 5
        val kopeck = 14_000_00
        val amount = 41_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }
    @Test
    fun limitCheck_5_true() {
        val input = 5
        val kopeck = 14_000_00
        val amount = 39_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(true, result)
    }
    @Test
    fun limitCheck_error() {
        val input = 6
        val kopeck = 14_000_00
        val amount = 39_000_00

        val result = limitCheck(input1 = input, inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }

    @Test
    fun checkingLimit_Day() {
        val kopeck = 160_000_00
        val amount = 500_000_00

        val result = checkingLimit(inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }
    @Test
    fun checkingLimit_Month() {
        val kopeck = 100_000_00
        val amount = 610_000_00

        val result = checkingLimit(inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(false, result)
    }
    @Test
    fun checkingLimit_true() {
        val kopeck = 140_000_00
        val amount = 500_000_00

        val result = checkingLimit(inputKopeck = kopeck, amountOfPreviousTransfers = amount)

        assertEquals(true, result)
    }
}
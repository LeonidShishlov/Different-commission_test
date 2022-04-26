import kotlin.math.roundToInt

const val MAESTRO_MASTERCARD_LIMIT_FREE = 75_000_00
const val MAESTRO_MASTERCARD_PERCENT = 0.006
const val MAESTRO_MASTERCARD_SUM_PERCENT = 20_00
const val VISA_MIR_PERCENT = 0.0075
const val VISA_MIR_MIN_35 = 35_00
const val LIMIT_DAY_OTHER_CARD = 150_000_00
const val LIMIT_MONTH_OTHER_CARD = 600_000_00
const val LIMIT_DAY_VK_PAY = 15_000_00
const val LIMIT_MONTH_VK_PAY = 40_000_00
const val COMMISSION_MORE_35 = 466667


enum class TypesOfPayments {
    MIR, VISA, MAESTRO, MASTERCARD, VK_PAY
}

fun main() {
    var amountOfPreviousTransfersMastercard = 0
    var amountOfPreviousTransfersMaestro = 0
    var amountOfPreviousTransfersVisa = 0
    var amountOfPreviousTransfersMir = 0
    var amountOfPreviousTransfersVkPay = 0
    var commission: Int

    while (true) {
        print("Введите сумму перевода: ")
        val input = readLine()?.toInt() ?: return
        val inputKopeck = input * 100
        println(
            "Выберете платежную систему: " +
                    "1. MIR" +
                    " 2. VISA" +
                    " 3. MAESTRO" +
                    " 4. MASTERCARD" +
                    " 5. VK PAY" +
                    " 6. Выход"

        )
        val input1 = readLine()?.toInt() ?: return
        when (input1) {
            1 -> {
                if (!limitCheck(
                        input1,
                        inputKopeck,
                        amountOfPreviousTransfersMir
                    )
                ) {
                    break
                } else{  commission = calculateCommission(
                    inputKopeck,
                    TypesOfPayments.MIR,
                    amountOfPreviousTransfers = amountOfPreviousTransfersMir
                )

                }
                amountOfPreviousTransfersMir += inputKopeck

            }
            2 -> {
                if (!limitCheck(
                        input1,
                        inputKopeck,
                        amountOfPreviousTransfersVisa
                    )
                ) {
                    break
                } else  commission =calculateCommission(
                    inputKopeck,
                    TypesOfPayments.VISA,
                    amountOfPreviousTransfers = amountOfPreviousTransfersVisa
                )
                amountOfPreviousTransfersVisa += inputKopeck

            }
            3 -> {
                if (!limitCheck(
                        input1,
                        inputKopeck,
                        amountOfPreviousTransfersMaestro
                    )
                ) {
                    break
                } else  commission =calculateCommission(
                    inputKopeck,
                    TypesOfPayments.MAESTRO,
                    amountOfPreviousTransfers = amountOfPreviousTransfersMaestro
                )
                amountOfPreviousTransfersMaestro += inputKopeck

            }
            4 -> {
                if (!limitCheck(
                        input1,
                        inputKopeck,
                        amountOfPreviousTransfersMastercard
                    )
                ) {
                    break
                } else  commission =calculateCommission(
                    inputKopeck,
                    TypesOfPayments.MASTERCARD,
                    amountOfPreviousTransfers = amountOfPreviousTransfersMastercard
                )
                amountOfPreviousTransfersMastercard += inputKopeck

            }
            5 -> {
                if (!limitCheck(
                        input1,
                        inputKopeck,
                        amountOfPreviousTransfersVkPay
                    )
                ) {
                    break
                } else  commission =calculateCommission(
                    inputKopeck,
                    TypesOfPayments.VK_PAY,
                    amountOfPreviousTransfers = amountOfPreviousTransfersVkPay
                )
                amountOfPreviousTransfersVkPay += inputKopeck

            }
            6 -> break
            else -> error("Такой пункт меню отсутствует.")
        }
        val commissionInRuble = commission/100
        val commissionInKopeck = commission % 100

        println("Переводод с комиссией составит: ${commissionInRuble + input} рублей $commissionInKopeck копеек")
    }
}

fun calculateCommission(
    inputKopeck: Int,
    typesOfPayments: TypesOfPayments,
    amountOfPreviousTransfers: Int
): Int {

    return when (typesOfPayments) {
        TypesOfPayments.MAESTRO, TypesOfPayments.MASTERCARD -> {

            if (amountOfPreviousTransfers <= MAESTRO_MASTERCARD_LIMIT_FREE && inputKopeck < MAESTRO_MASTERCARD_LIMIT_FREE) {
                return 0
            } else {
                val gd = inputKopeck * MAESTRO_MASTERCARD_PERCENT
                return gd.roundToInt() + MAESTRO_MASTERCARD_SUM_PERCENT
            }

        }
        TypesOfPayments.MIR, TypesOfPayments.VISA -> {
            if (inputKopeck <= COMMISSION_MORE_35) {
                VISA_MIR_MIN_35
            } else {
                val gd = inputKopeck * VISA_MIR_PERCENT
                gd.roundToInt()
            }
        }
        TypesOfPayments.VK_PAY -> 0
    }
}

fun limitCheck(
    input1: Int,
    inputKopeck: Int,
    amountOfPreviousTransfers: Int

): Boolean {
    return when (input1) {
        1 -> checkingLimit(inputKopeck, amountOfPreviousTransfers)
        2 -> checkingLimit(inputKopeck, amountOfPreviousTransfers)
        3 -> checkingLimit(inputKopeck, amountOfPreviousTransfers)
        4 -> checkingLimit(inputKopeck, amountOfPreviousTransfers)
        5 -> {
            return if (amountOfPreviousTransfers > LIMIT_MONTH_VK_PAY) {
                println("Лимит по выбранной карте в этом месяце привышен")
                false
            } else if (inputKopeck > LIMIT_DAY_VK_PAY) {
                println("Лимит по выбранной карте сегодня привышен")
                false
            } else true
        }
        else -> return false
    }

}

fun checkingLimit(
    inputKopeck: Int,
    amountOfPreviousTransfers: Int,
): Boolean {

    return if (amountOfPreviousTransfers > LIMIT_MONTH_OTHER_CARD) {
        println("Лимит по выбранной карте в этом месяце привышен")
        false
    } else if (inputKopeck > LIMIT_DAY_OTHER_CARD) {
        println("Лимит по выбранной карте сегодня привышен")
        false
    } else true
}
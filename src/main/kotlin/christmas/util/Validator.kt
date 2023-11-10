package christmas.util

object Validator {
    private val dateRegex = "^([1-9]|[12]\\d|3[01])\$".toRegex()
    private val menuRegex = "^([가-힣]+)-([1-9]\\d*)$".toRegex()

    const val MAX_QUANTITY = 20

    fun isDateInRange(input: String) {
        require(dateRegex.matches(input)) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
    }

    fun isMenuMatchRegex(input: String) {
        require(menuRegex.matches(input)) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
    }

    fun isMenuQuantityInRange(inputList: List<String>) {
        var quantity = 0
        for (input in inputList) {
            val matchResult = menuRegex.find(input)
            if (matchResult != null) quantity += matchResult.groupValues[2].toInt()
        }
        require(quantity <= MAX_QUANTITY){"[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."}
    }
}
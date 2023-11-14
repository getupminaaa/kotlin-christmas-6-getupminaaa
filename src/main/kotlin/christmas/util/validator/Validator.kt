package christmas.util.validator

import christmas.util.enums.ErrorMessage

object Validator {
    private val dateRegex = "^([1-9]|[12]\\d|3[01])\$".toRegex()
    private val menuRegex = "^([가-힣]+)-([1-9]\\d*)$".toRegex()
    private const val MAX_QUANTITY = 20

    fun isDateInRange(input: String) = require(dateRegex.matches(input)) { ErrorMessage.NOT_VALID_DATE.msg }

    fun isMenuMatchRegex(input: String) = require(menuRegex.matches(input)) { ErrorMessage.NOT_VALID_ORDER.msg }

    fun isMenuQuantityInRange(inputs: List<String>) {
        var quantity = 0
        for (input in inputs) {
            val matchResult = menuRegex.find(input)
            if (matchResult != null) quantity += matchResult.groupValues[2].toInt()
        }
        require(quantity <= MAX_QUANTITY) { ErrorMessage.NOT_VALID_ORDER.msg }
    }

    fun isMenuDuplicate(inputs: List<String>) {
        val menuNames = mutableListOf<String>()
        for (input in inputs) {
            val matchResult = menuRegex.find(input)
            if (matchResult != null) menuNames.add(matchResult.groupValues[1])
        }
        require(menuNames.size == menuNames.toSet().size) { ErrorMessage.NOT_VALID_ORDER.msg }
    }

    fun isOrderInMenu(isInMenu: Boolean) = require(isInMenu) { ErrorMessage.NOT_VALID_ORDER.msg }

    fun isOrderContainsFood(notOnlyBeverage: Boolean) = require(notOnlyBeverage) { ErrorMessage.NOT_VALID_ORDER.msg }
}
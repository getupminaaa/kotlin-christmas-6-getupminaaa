package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.util.enums.ProgramMessages
import christmas.util.validator.Validator.isDateInRange
import christmas.util.validator.Validator.isMenuDuplicate
import christmas.util.validator.Validator.isMenuMatchRegex
import christmas.util.validator.Validator.isMenuQuantityInRange

class InputView {
    fun getVisitDate(): Int {
        println(ProgramMessages.VISIT_DATE_PROMPT.msg)
        val userInput = Console.readLine()
        isDateInRange(userInput)
        return userInput.toInt()
    }

    fun getOrderMenu(): List<Map<String, Int>> {
        println(ProgramMessages.ORDER_MENU_PROMPT.msg)
        val userInput = Console.readLine().split(',')
        userInput.forEach { isMenuMatchRegex(it) }
        isMenuDuplicate(userInput)
        isMenuQuantityInRange(userInput)
        return userInput.map {
            val (menu, quantity) = it.split('-')
            mapOf(menu to quantity.toInt())
        }
    }
}
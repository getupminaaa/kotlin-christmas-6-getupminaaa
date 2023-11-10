package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.util.Validator.isDateInRange
import christmas.util.Validator.isMenuMatchRegex
import christmas.util.Validator.isMenuQuantityInRange

class InputView {

    val inputDate: String
        get() = _inputDate
    private var _inputDate = ""

    private var inputMenuList = listOf<String>()

    val inputMenu: List<String>
        get() = _inputMenu
    private var _inputMenu = listOf<String>()

    init {
        _inputDate = getValidVisitDate()
        _inputMenu = getValidOrderedMenu()
    }

    private fun getVisitDate(): String {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        return Console.readLine()
    }

    private fun getValidVisitDate(): String {
        return try {
            val userInput = getVisitDate()
            isDateInRange(userInput)
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidVisitDate()

        }
    }


    private fun getOrderedMenuList(): List<String> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        return Console.readLine().split(',')
    }

    private fun getValidOrderedMenu(): List<String> {
        return try {
            val userInput = getOrderedMenuList()
            userInput.forEach { isMenuMatchRegex(it) }
            isMenuQuantityInRange(userInput)
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidOrderedMenu()
        }
    }
}
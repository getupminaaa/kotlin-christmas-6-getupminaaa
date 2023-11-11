package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.util.Validator.isDateInRange
import christmas.util.Validator.isMenuDuplicate
import christmas.util.Validator.isMenuMatchRegex
import christmas.util.Validator.isMenuQuantityInRange

class InputView {
    fun getVisitDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val userInput = Console.readLine()
        isDateInRange(userInput)
        return userInput.toInt()
    }


    fun getOrderMenu(): List<Map<String, Int>> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val userInput = Console.readLine().split(',')
        val orderSheet = mutableListOf<Map<String,Int>>()
        userInput.forEach {
            isMenuMatchRegex(it)
            val orderMenuQuantity = it.split('-')
            orderSheet.add(mapOf(orderMenuQuantity[0] to orderMenuQuantity[1].toInt()))
        }
        isMenuDuplicate(userInput)
        isMenuQuantityInRange(userInput)
        return orderSheet
    }
}
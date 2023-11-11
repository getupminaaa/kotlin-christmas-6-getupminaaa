package christmas.controller

import christmas.model.DateManager
import christmas.model.Menu
import christmas.model.MenuManager
import christmas.util.Validator
import christmas.util.Validator.isDateInRange
import christmas.util.Validator.isOrderInMenu
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val outputView = OutputView()
    private val inputView = InputView()
    private val menu = Menu()

    private var validDate = 0
    private var validOrder = listOf<Map<String, Int>>()

    init {
        validDate = getValidDate()
        validOrder = getValidOrder()
    }

    fun run(){
        val dateManager = DateManager(validDate)
        outputView.printEventMsg(dateManager.visitDate)

    }
    private fun getValidDate(): Int {
        return try {
            val userInput = inputView.getVisitDate()
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidDate()
        }
    }
    private fun getValidOrder(): List<Map<String, Int>> {
        return try {
            val userInput = inputView.getOrderedMenuList()
            menuCheck(userInput)
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidOrder()
        }
    }

    private fun menuCheck(userInput: List<Map<String, Int>>) {
        userInput.flatMap { it.keys }.forEach { isOrderInMenu(menu.isItemInMenu(it)) }
    }
}
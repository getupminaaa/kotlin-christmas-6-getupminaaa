package christmas.controller

import christmas.model.DateManager
import christmas.model.Menu
import christmas.model.MenuManager
import christmas.util.Validator.isOrderContainsFood
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

    fun run() {
        outputView.printEventMsg(validDate)

        val menuManager = MenuManager(validOrder)
        outputView.printMenu(validOrder)

        menuManager.calculateTotalPrice(menu.getMenuItemPrice(menuManager.orderedMenuNameList))
        outputView.printTotalPrice(menuManager.totalPrice)

        val dateManager = DateManager(validDate)

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
            onlyBeverage(userInput)
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidOrder()
        }
    }
    private fun menuCheck(userInput: List<Map<String, Int>>) {
        userInput.flatMap { it.keys }.forEach { isOrderInMenu(menu.isItemInMenu(it)) }
    }
    private fun onlyBeverage(userInput: List<Map<String, Int>>) {
        val temp = userInput.flatMap { it.keys }
        isOrderContainsFood(menu.areItemsNotOnlyBeverage(temp))
    }



}
